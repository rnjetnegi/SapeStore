package com.sapestore.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.CartItem;
import com.sapestore.hibernate.entity.WishListNew;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.WishListUserVO;
import com.sapestore.vo.WishListVO;



@Repository
@Transactional
public class WishListDao {

@Autowired
private HibernateTemplate hibernateTemplate;

	Logger log = Logger.getLogger(WishListDao.class.getName());

	public WishListUserVO addToWishlist(WishListUserVO wishlist,String userId,String isbn){
		Book book=null;
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn",isbn.trim());
		if(result.size() > 0)
	     book=(Book) result.get(0);
		
		WishListNew wishItem=new WishListNew();
		if(book!=null)
		{
			wishItem.setUserId(userId);
			wishItem.setIsbn(isbn);
			
			
			wishItem.setBookTitle(book.getBookTitle());
			wishItem.setBookAuthor(book.getBookAuthor());
			wishItem.setBookPrice(book.getBookPrice());
			wishItem.setBookThumbImage(book.getBookThumbImage());			
		   
		    hibernateTemplate.save(wishItem);
		    WishListVO wishListVo = new WishListVO(userId, book);
		    wishlist.getWishListItems().add(wishListVo);
		}
		return wishlist;
	}


	public WishListUserVO viewWishlist(String userId){
		WishListUserVO wishlist=new WishListUserVO();
		List<WishListVO>itemList=new ArrayList<WishListVO>();
		wishlist.setWishListItems(itemList);
		List result=hibernateTemplate.findByNamedQueryAndNamedParam("WishListBook.view", "userId", userId);
		int i=0;
		while(i<result.size()){
			
			WishListNew wishItem=(WishListNew) result.get(i);
		
			WishListVO item=new WishListVO(wishItem);
			wishlist.getWishListItems().add(item);
			i++;
		}
		return wishlist;
	}
	
	public WishListUserVO removeFromWishlist(WishListUserVO wishlist, String userId, String isbn) {
		WishListNew wishItem=null;
		String []param={"userId","isbn"};
		String []values={userId.trim(),isbn.trim()};
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam( "WishListBook.remove", param,values);
		if(!result.isEmpty()){
			wishItem=(WishListNew) result.get(0);
			hibernateTemplate.delete(wishItem);
		}
		if(wishlist.getWishListItems().size()>0)
		{
			List<WishListVO> items=wishlist.getWishListItems();
			for(int i=0;i<items.size();i++){
				WishListVO tempItem=items.get(i);
				if(tempItem.getIsbn().equalsIgnoreCase(isbn)){
					items.remove(i);
		}
			
	
		
	}		
		}	return wishlist;
		}
	
	public WishListUserVO moveFromWishlist (WishListUserVO wishlist, String userId, String isbn){
		WishListNew wishItem=null;
		
		String []param={"userId","isbn"};
		String []values={userId.trim(),isbn.trim()};
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam( "WishListBook.moveToCart", param,values);
		//List result1=  hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn",isbn.trim());
		if(result.size() > 0)
			wishItem=(WishListNew) result.get(0);
		CartItem cartItem=new CartItem();
		if(wishItem!=null)
		{
			cartItem.setUserId(userId);
			cartItem.setIsbn(isbn);
			cartItem.setQuantity(1);
			cartItem.setType("purchase");
			cartItem.setTitle(wishItem.getBookTitle());
	        cartItem.setAuthor(wishItem.getBookAuthor());
		    cartItem.setBookPrice(wishItem.getBookPrice());
		    cartItem.setImage(wishItem.getBookThumbImage());			
		    hibernateTemplate.save(cartItem);
		    System.out.println("saved");
		  /*  CartItemVO cartItemVo = new CartItemVO(userId, book, type);
		    cart.getCartItems().add(cartItemVo);*/
		}		
		if(!result.isEmpty()){
			wishItem=(WishListNew) result.get(0);
			hibernateTemplate.delete(wishItem);
		}
		if(wishlist.getWishListItems().size() > 0)
		{
			List<WishListVO> items=wishlist.getWishListItems();
			for(int i=0;i<items.size();i++){
				WishListVO tempItem=items.get(i);
				if(tempItem.getIsbn().equalsIgnoreCase(isbn)){
					items.remove(i);
				}
			}
		}
		return wishlist;
	}


	public boolean checkBookIntoWishList(String userId, String isbn) {
		String []param={"userId","isbn"};
		String []values={userId.trim(),isbn.trim()};
		List result=  hibernateTemplate.findByNamedQueryAndNamedParam( "WishListBook.moveToCart", param,values);
		if(result.size()==0){
			return false;
		}
		return true;
	}
}
