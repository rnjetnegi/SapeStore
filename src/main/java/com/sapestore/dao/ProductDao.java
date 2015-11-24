package com.sapestore.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for retrieving the book's list from the database. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
@Repository
public class ProductDao {
	
	private SessionFactory factory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory() ;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}// = new HibernateTemplate(factory);
		
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ProductDao.class.getName());
    
    /**
     * Method to fetch the book list from the database.
     * 
     * @param categoryId
     * @param checkMeFromSession 
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
	@SuppressWarnings("unchecked")
	public List<Book> getBookList(int categoryId, Object checkMeFromSession) throws SapeStoreException {		
		List<Book> listBook = null;
		if(categoryId == 0) {
			listBook = (List<Book>) hibernateTemplate.findByNamedQuery("Book.findAll");
		}
		else		
		listBook = (List<Book>) hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByCategoryId", "categoryId", categoryId);
	
		return listBook;		
	}
	
	 /**
     * Method to fetch the book's category list.
     * 
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
	@SuppressWarnings("unchecked")
	public List<BookCategory> getBookCategoryList()	throws SapeStoreException {
		List<BookCategory> listBookCategories = (List<BookCategory>) hibernateTemplate.findByNamedQuery("BookCategory.findAll");
		return listBookCategories;		
	}
	
	/**
	 * deleteBook method updates the quantity of the selected book to zero in the database
	 * 
	 * @param isbn
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public void deleteBook(String isbn) throws SapeStoreException {
		LOGGER.debug(" BookDao.deleteBook method: START");
		
		Book book = hibernateTemplate.get(Book.class, isbn.trim());
		if(book!= null){
			book.setQuantity(0);		
			hibernateTemplate.saveOrUpdate(book);
		}
	}
	
	/**
	 * Method to add a new book to the database.
	 * 
	 * @param vo
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */	
	public void addNewBooks(BookVO vo) throws SapeStoreException {
		LOGGER.debug(" ProductDao.addNewBooks method: START");
		try{
			Book book = new Book();
			book.setAverageRating(new BigDecimal(0));
			book.setIsbn(vo.getIsbn());
			book.setPublisherName(vo.getPublisherName().trim());
			book.setCategoryId(vo.getCategoryId());
			book.setBookTitle(vo.getBookTitle());		
			book.setQuantity(vo.getQuantity());
			book.setBookAuthor(vo.getBookAuthor());		
			book.setBookThumbImage(vo.getThumbPath());		
			book.setBookFullImage(vo.getFullPath());
			book.setBookPrice(new BigDecimal(vo.getBookPrice()));		
			book.setBookShortDescription(vo.getBookShortDesc());		
			book.setBookDetailDescription(vo.getBookDetailDesc());		
			book.setRentAvailability(vo.getRentAvailable());		
			book.setRentPrice(vo.getRentPrice());		
			book.setCreatedDate(new java.util.Date());		
			book.setUpdatedDate(new java.util.Date());		
			book.setIsActive("Y");

			hibernateTemplate.save(book);
		}		
		catch (SapeStoreSystemException se) {
				LOGGER.fatal("A DB exception occured while inserting the book's information", se);
			}
		LOGGER.debug(" ProductDao.addNewBooks method: END ");
	}

		
	/**
	 * Update Book method updates the detail of the corresponding book.
	 * 
	 * @param updateInventoryBean
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public void updateBooks(BookVO updateInventoryBean) throws SapeStoreException {
		LOGGER.debug(" ProductDao.updateBooks method: START ");
	
		try {
			
			Book book = hibernateTemplate.get(Book.class, updateInventoryBean.getOldIsbn().trim());
			
			if(book != null){	
				
				
				book.setPublisherName(updateInventoryBean.getPublisherName().trim());				
				book.setBookTitle(updateInventoryBean.getBookTitle());			
				book.setQuantity(updateInventoryBean.getQuantity());					
				book.setBookAuthor(updateInventoryBean.getBookAuthor());			
				book.setBookPrice(new BigDecimal(updateInventoryBean.getBookPrice()));			
				book.setBookShortDescription(updateInventoryBean.getBookShortDesc());
				book.setRentAvailability(updateInventoryBean.getRentAvailable());			
				book.setRentPrice(updateInventoryBean.getRentPrice());			
				book.setBookDetailDescription(updateInventoryBean.getBookDetailDesc());
				if(updateInventoryBean.getCategoryId() != null){
				book.setCategoryId(updateInventoryBean.getCategoryId());
				}
				if(updateInventoryBean.getThumbPath() !=null)
				book.setBookThumbImage(updateInventoryBean.getThumbPath());	
				if(updateInventoryBean.getFullPath() !=null)
				book.setBookFullImage(updateInventoryBean.getFullPath());			
				book.setUpdatedDate(new java.util.Date());
				hibernateTemplate.update(book);
				LOGGER.debug(" Book is updated ");
				System.out.println("Book is updated");
			}
		}catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
		}
		LOGGER.debug(" ProductDao.updateBooks method: END ");
	}
	
	/**
     * Method to get book details from the database.
     *
     * @param isbn
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
    public BookVO getBookDetails(String isbn) throws SapeStoreException {    	    	
		LOGGER.debug(" ProductDao.getBookDetails method: START");
	
		Book book = null;
		List commentList=null;
		List commentListVo= new ArrayList();
		try{
			/**
			 * Find Book Details By ISBN
			 */
			List result=  hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn",isbn.trim());
			if(result.size() > 0)
				book=(Book) result.get(0);
			/**
			 * Find BOOK Comments corresponding to ISBN
			 */
			commentList=hibernateTemplate.findByNamedQueryAndNamedParam("BookRatingComments.findByIsbn","isbn",isbn.trim());
		}
		
		catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
			// Return Null if no ISBN Found
			return null;
	    }
		
		for(int i = 0; i < commentList.size(); i++){
			BookRatingCommentsVO bookRatingVo = new BookRatingCommentsVO();
			bookRatingVo.setBookCommentDate(((BookRatingComments)commentList.get(i)).getBookCommentDate());
			bookRatingVo.setBookComments(((BookRatingComments)commentList.get(i)).getBookComments());
			bookRatingVo.setBookRating(((BookRatingComments)commentList.get(i)).getBookRating());
			bookRatingVo.setCommentId(((BookRatingComments)commentList.get(i)).getCommentId());
			bookRatingVo.setIsbn(((BookRatingComments)commentList.get(i)).getIsbn());
			bookRatingVo.setUserId(((BookRatingComments)commentList.get(i)).getUserId());
			commentListVo.add(bookRatingVo);
		}
		
    	return setBookDetailBean(book,commentListVo);    	
    }
	
	/**
     * Method to get book details from the database.
     *
     * @param isbn
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
    public BookVO getBookDetailsForDate(String isbn) throws SapeStoreException {
		LOGGER.debug(" ProductDao.getBookDetails method: START");
	
		Book book = null;
		List commentList=null;
		List commentListVo= new ArrayList();
		try{
			/**
			 * Find Book Details By ISBN
			 */
			List result=  hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn",isbn.trim());
			if(result.size() > 0)
				book=(Book) result.get(0);
			/**
			 * Find BOOK Comments corresponding to ISBN
			 */
			commentList=hibernateTemplate.findByNamedQueryAndNamedParam("BookRatingComments.findByIsbn","isbn",isbn.trim());
		}
		
		catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
			// Return Null if no ISBN Found
			return null;
	    }
		
		for(int i = 0; i < commentList.size(); i++){
			BookRatingCommentsVO bookRatingVo = new BookRatingCommentsVO();
			bookRatingVo.setBookCommentDate(((BookRatingComments)commentList.get(i)).getBookCommentDate());
			bookRatingVo.setBookComments(((BookRatingComments)commentList.get(i)).getBookComments());
			bookRatingVo.setBookRating(((BookRatingComments)commentList.get(i)).getBookRating());
			bookRatingVo.setCommentId(((BookRatingComments)commentList.get(i)).getCommentId());
			bookRatingVo.setIsbn(((BookRatingComments)commentList.get(i)).getIsbn());
			bookRatingVo.setUserId(((BookRatingComments)commentList.get(i)).getUserId());
			commentListVo.add(bookRatingVo);
		}
		
    	return setBookDetailBean(book,commentListVo);    	
    }
    public BookVO getBookDetailsForRatings(String isbn) throws SapeStoreException {    	    	
		LOGGER.debug(" ProductDao.getBookDetails method: START");
	
		Book book = null;
		List commentList=null;
		List commentListVo= new ArrayList();
		try{
			/**
			 * Find Book Details By ISBN
			 */
			List result=  hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn",isbn.trim());
			if(result.size() > 0)
				book=(Book) result.get(0);
			/**
			 * Find BOOK Comments corresponding to ISBN
			 */
			commentList=hibernateTemplate.findByNamedQueryAndNamedParam("BookRatingComments.findByIsbnForRatings","isbn",isbn.trim());
		}
		
		catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
			// Return Null if no ISBN Found
			return null;
	    }
		
		for(int i = 0; i < commentList.size(); i++){
			BookRatingCommentsVO bookRatingVo = new BookRatingCommentsVO();
			bookRatingVo.setBookCommentDate(((BookRatingComments)commentList.get(i)).getBookCommentDate());
			bookRatingVo.setBookComments(((BookRatingComments)commentList.get(i)).getBookComments());
			bookRatingVo.setBookRating(((BookRatingComments)commentList.get(i)).getBookRating());
			bookRatingVo.setCommentId(((BookRatingComments)commentList.get(i)).getCommentId());
			bookRatingVo.setIsbn(((BookRatingComments)commentList.get(i)).getIsbn());
			bookRatingVo.setUserId(((BookRatingComments)commentList.get(i)).getUserId());
			commentListVo.add(bookRatingVo);
		}
		
    	return setBookDetailBean(book,commentListVo);    	
    }

	/**
	 * Converts the Map representation of book details HashMap to its DO
	 * representation
	 * 
	 * @param hash
	 * @return
	 */
    private BookVO setBookDetailBean(Book book,List commentListVo) {
    	BookVO vo = null;
        if (book != null) {
        	vo = new BookVO();
            vo.setIsbn(book.getIsbn());
            vo.setBookTitle(book.getBookTitle());
            vo.setBookAuthor(book.getBookAuthor());
            vo.setPublisherName(book.getPublisherName());
            vo.setBookShortDesc(book.getBookShortDescription());
            vo.setBookDetailDesc(book.getBookDetailDescription());
            vo.setBookPrice(book.getBookPrice().toString());
            vo.setThumbPath(book.getBookThumbImage());
            vo.setFullPath(book.getBookFullImage());
            vo.setQuantity(book.getQuantity());
            vo.setRentPrice(book.getRentPrice());
            vo.setCategoryName(book.getCategoryName());
            //vo.setRating(new BigDecimal(0));
            vo.setCommentList(commentListVo);
        } 
        return vo;
    }
    /**
     * Method to get book details from the database.
     *
     * @param isbn,comment
     * @return void
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
    
    public void addBookCommentRating(String isbn,String userId,String comment,double rating)throws SapeStoreException {
		LOGGER.debug(" ProductDao.getBookDetails method: START");
		BookRatingComments book;
		Long date=Calendar.getInstance().getTime().getTime();
	    Date updatedDate = new Date(date);
		String []param={"isbn","userId"};
		String []values={isbn,userId};
	    List result =hibernateTemplate.findByNamedQueryAndNamedParam("BookRatingComments.findByIsbnAnduserId", param, values);	
		
	    if(!result.isEmpty()){
			book=(BookRatingComments)result.get(0);
			book.setBookComments(comment);
			book.setBookCommentDate(updatedDate);
			book.setBookRating(rating);
			hibernateTemplate.update(book);
		}else{
			book=new BookRatingComments();
			book.setIsbn(isbn);
			book.setUserId(userId);
			book.setBookComments(comment);
			book.setBookRating(rating);
			book.setBookCommentDate(updatedDate);
			hibernateTemplate.save(book);
		}
		
	}
    
    public BookRatingCommentsVO getBookCommentRating(String isbn, String userId) {
    	
    	String []param={"isbn","userId"};
		String []values={isbn.trim(),userId.trim()};
    	List result =hibernateTemplate.findByNamedQueryAndNamedParam("BookRatingComments.findByIsbnAnduserId", param, values);
    	BookRatingCommentsVO bookVo = new BookRatingCommentsVO();
    	if(result.size() > 0){
    		BookRatingComments book = (BookRatingComments)result.get(0);
	    	bookVo.setBookComments(book.getBookComments());
	    	bookVo.setBookRating(book.getBookRating());
	    	return bookVo;
    	}
    	bookVo.setBookComments("");
    	bookVo.setBookRating(0.0);
		return bookVo;
	}
}
