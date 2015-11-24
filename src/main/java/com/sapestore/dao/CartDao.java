package com.sapestore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.CartItem;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.CartVO;
import com.sapestore.vo.ShoppingCartVO;

/**
 * DAO class for order management module
 */
@Repository
@Transactional
public class CartDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
    
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(CartDao.class.getName());
	
	
	public CartVO viewCart(String userId){
		CartVO cart=new CartVO();
		List<CartItemVO>itemList=new ArrayList<CartItemVO>();
		cart.setCartItems(itemList);
		cart.setUserId(userId);
		List result=hibernateTemplate.findByNamedQueryAndNamedParam("CartItem.findAll", "userId", userId);
		int i=0;
		while(result!=null &&i<result.size()){
			
			CartItem cartItem=(CartItem) result.get(i);
			CartItemVO item=new CartItemVO(cartItem);
			cart.getCartItems().add(item);
			i++;
		}
		cart.setTotalQuantity(i);
		return cart;
	}
	
	
	/**
	 * This Method checks whether book is already in the cart of the user before adding into the database
	 * @param cart
	 * @param userId
	 * @param isbn
	 * @param type
	 * @return
	 */
	public boolean checkBookIntoCart(String userId,String isbn,String type){
		String []param={"userId","isbn","type"};
		String []values={userId.trim(),isbn.trim(),type.trim()};
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam("CartItem.UniqueBookformCartItemTable", param,values);
		if(result.size()==0){
			return false;
		}
		return true;
	}
	
	/**
	 * iterate through all cart items stored in cookie by guest and add them to Db
	 * if not already in the cart table
	 * @param cookieCart
	 * @param userId
	 * @return CartVO to be added as a model attribute
	 */
	public CartVO addCookieToCart(CookieCart cookieCart, String userId, HttpServletResponse response){
		CartVO cart = this.viewCart(userId);
		List<CartItemVO> cartList = cart.getCartItems();
		if(cookieCart==null){
			return cart;
		}
		List<CartItemVO> cookieList = cookieCart.getCartItems();
		ListIterator<CartItemVO> cookieIter = cookieList.listIterator();
		ListIterator<CartItemVO> cartIter;
		CartItemVO cookieItem;
		CartItemVO cartItem;
		boolean recurringItem = false;
		
		//iterate through cookie cart items and add new items to cart DB table
		while(cookieIter.hasNext()){
			cookieItem = cookieIter.next();
			cookieItem.setUserId(userId);
			
			//if cart list is not empty, check for repeated cart items in the cartList
			if(!cartList.isEmpty()){
				cartIter = cartList.listIterator();
				while(cartIter.hasNext()){
					cartItem = cartIter.next();
					String bookType = cartItem.getType();
					String currUserId = cartItem.getUserId();
					String bookIsbn = cartItem.getIsbn();
					if(cookieItem.equals(bookType, currUserId, bookIsbn)){
						recurringItem = true;
						break;
					}
				}
			}
			
			//if cookie cart item was not a recurring item from the cart DB table then store it
			if(recurringItem == false){
				hibernateTemplate.save(new CartItem(cookieItem));
				cart.getCartItems().add(cookieItem);
			}
			else{
				recurringItem = false;
			}
		}
		cookieCart.removeCartCookie(response);
		return cart;
	}
	
	public CartVO addToCart(CartVO cart,String userId,String isbn,String type){
		Book book=null;
		
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn",isbn.trim());
		if(result.size() > 0)
	     book=(Book) result.get(0);
		
		CartItem cartItem=new CartItem();
		if(book!=null)
		{
			cartItem.setUserId(userId);
			cartItem.setIsbn(isbn);
			cartItem.setQuantity(1);
			cartItem.setType(type);
			cartItem.setTitle(book.getBookTitle());
	        cartItem.setAuthor(book.getBookAuthor());
		    cartItem.setBookPrice(book.getBookPrice());
		    cartItem.setImage(book.getBookThumbImage());			
		    cartItem.setRentPrice(book.getRentPrice());	
		    hibernateTemplate.save(cartItem);
		    CartItemVO cartItemVo = new CartItemVO(userId, book, type);
		    cart.getCartItems().add(cartItemVo);
		}
		return cart;
	}

	public CartVO removeFromCart(CartVO cart, String userId,
			String type, String isbn) {
		CartItem cartItem=null;
		String []param={"userId","isbn","type"};
		String []values={userId.trim(),isbn.trim(),type.trim()};
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam( "CartItem.UniqueBookformCartItemTable", param,values);
		if(!result.isEmpty()){
			cartItem=(CartItem) result.get(0);
			hibernateTemplate.delete(cartItem);
		}
		if(cart.getCartItems().size()>0)
		{  
			List<CartItemVO> items=cart.getCartItems();
			for(int i=0;i<items.size();i++){
				CartItemVO tempItem=items.get(i);
				if(tempItem.getIsbn().equalsIgnoreCase(isbn)&&tempItem.getType().equalsIgnoreCase(type)){
					items.remove(i);
				}
			}
		}
		
		return cart;
	}
	
   public int checkQuantity(String isbn){
	   int quanity=0;
	   List result=hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn", isbn);
	   if(!result.isEmpty()){
		   Book book=(Book) result.get(0);
		   quanity=book.getQuantity();
	   }
	   return quanity;
   }


public void updateQuantity(String userId, String isbn, String type,	int quantity) {
	// TODO Auto-generated method stub
	String []param={"userId","isbn","type"};
	String []values={userId.trim(),isbn.trim(),type.trim()};
	List result=  hibernateTemplate.findByNamedQueryAndNamedParam( "CartItem.UniqueBookformCartItemTable", param,values);
	if(result!=null || result.size()>0){
		CartItem cartItem = (CartItem) result.get(0);
		cartItem.setQuantity(quantity);
		hibernateTemplate.update(cartItem);
	}
}


}
