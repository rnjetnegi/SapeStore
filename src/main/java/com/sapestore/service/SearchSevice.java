package com.sapestore.service;

import java.util.ArrayList;
import java.util.List;




import org.springframework.stereotype.Service;

import com.sapestore.hibernate.entity.SearchBook;


/**
 * Service interface for Book Search functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	          MESSAGE 
 * 1.0 		21-10-2015 	SAPIENT  Initial version
 */


@Service
public interface SearchSevice {

	/**
	 * Returns list of books matching the keyWord
	 * @param keyWord
	 * @return
	 */
	ArrayList<SearchBook> searchBook(String keyWord);
	
	
	/**
	 * Returns list of books for PredictiveSearch
	 * @param searchText
	 * @return
	 */
    ArrayList<String> searchText(String searchText);
    
    
    /**
	 * Returns list of books in order of descending usercomments
	 * @param books
	 * @return
	 */
    ArrayList<SearchBook> SearchByComments(ArrayList<SearchBook> books);
}
