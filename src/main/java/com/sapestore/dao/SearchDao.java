package com.sapestore.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;



import com.sapestore.hibernate.entity.SearchBook;
import com.sapestore.hibernate.entity.BookCategory;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for searching book from database.
 *
 * CHANGE LOG VERSION    DATE            AUTHOR                  MESSAGE 
 *   1.0               21-10-2015    VINAY & NIMISHA             Initial
 * version
 */

@Repository
public class SearchDao {

@Autowired
private HibernateTemplate hibernateTemplate;

	Logger log = Logger.getLogger(SearchDao.class.getName());


	/**
	 * Method to fetch the book by Book Category for PredictiveSearch.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> predictSearchByCategory(String categoryName) {

		ArrayList<String> listBook = (ArrayList<String>) hibernateTemplate
				.findByNamedQueryAndNamedParam("BookCategory.PredictSearchByCategoryName",
						"categoryName",categoryName.toUpperCase().trim().replace("%", "@") + '%');
		return listBook;
	}
	
	
	
	
	/**
	 * Method to fetch the book by Book Name.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */ 
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<SearchBook> searchByBookName(String bookTitle) {

		ArrayList<SearchBook> listBook = (ArrayList<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookTitle",
						"bookTitle", '%' + bookTitle.toUpperCase().trim().replace("%", "@") + '%');
		return listBook;
	}

	/**
	 * Method to fetch the book by Book Name for PredictiveSearch.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> predictSearchByTitle(String bookTitle) {

		ArrayList<String> listBook = (ArrayList<String>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.PredictSearchByTitle",
						"bookTitle", bookTitle.toUpperCase().trim().replace("%", "@") + '%');
		return listBook;
	}

	/**
	 * Method to fetch the book by Book Author for PredictiveSearch.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> predictSearchByAuthor(String bookAuthor) {

		ArrayList<String> listBook = (ArrayList<String>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.PredictSearchByAuthor",
						"bookAuthor",bookAuthor.toUpperCase().trim().replace("%", "@") + '%');
		return listBook;
	}

	

	/**
	 * Method to fetch the book by Author.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SearchBook> searchByAuthor(String bookAuthor) {

		ArrayList<SearchBook> listBookAuthor = (ArrayList<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookAuthor",
						"bookAuthor", '%' + bookAuthor.toUpperCase().trim().replace("%", "@") + '%');

		return listBookAuthor;
	}

	/**
	 * Method to fetch the book by Categeory Name.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SearchBook> searchByCategeory(String categoryName) {

		ArrayList<SearchBook> listBookCategory = (ArrayList<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookCategory",
						"categoryName", '%' + categoryName.toUpperCase().trim().replace("%", "@") + '%');
		return listBookCategory;
	}

	/**
	 * Method to fetch the book by ISBN.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SearchBook> searchByISBN(String isbn) {

		ArrayList<SearchBook> listBookISBN = (ArrayList<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookISBN", "isbn",
						'%' + isbn.toUpperCase().trim().replace("%", "@") + '%');
		return listBookISBN;
	}

	
	/*@SuppressWarnings("unchecked")
	public List<Book> sortResultByBookTitle(String bookTitle) {

		List<Book> listBook = (List<Book>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.SortByBookTitle",
						"bookTitle", '%' + bookTitle + '%');
		return listBook;
	}*/
	
	
	/**
	 * Method to fetch the book on the basis of no of comments in desc order.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<SearchBook> searchByBookComments(String isbn){
		
		ArrayList<SearchBook> listBookComments = (ArrayList<SearchBook>) hibernateTemplate
		.findByNamedQueryAndNamedParam(
				"BookRatingComments.findByBookISBN", "isbn", isbn);
		
		return listBookComments;
		
	}

}
