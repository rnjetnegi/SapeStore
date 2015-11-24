package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.SearchDao;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.SearchBook;
import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;
import com.sapestore.partner.services.SSPartnerWebServicePortType;
import com.sapestore.service.SearchSevice;

/**
 * Service class for Book Search and Result display.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 21-10-2015 VINAY & NIMISHA Initial
 * version
 */
@Service("searchService")
public class SearchServiceImpl implements SearchSevice {

	@Autowired
	SearchDao search;     
	

	@Override
	public ArrayList<SearchBook> searchBook(String searchText) {
		ArrayList<SearchBook> listSearchByCategory = search.searchByCategeory(searchText);
		ArrayList<SearchBook> listSearchByName = search.searchByBookName(searchText);
		
		ArrayList<SearchBook> listSearchByAuthor = search.searchByAuthor(searchText);
		ArrayList<SearchBook> listSearchByISBN = search.searchByISBN(searchText);

		ArrayList<SearchBook> listBookFinal = new ArrayList<SearchBook>();
		listBookFinal.addAll(listSearchByCategory);
		listBookFinal.addAll(listSearchByName);

		listBookFinal.addAll(listSearchByAuthor);
		listBookFinal.addAll(listSearchByISBN);

		return listBookFinal;
	}
	
	/*public List<SearchBook> searchPartnerBook(String searchText) {
		List<SearchBook> listBookFinal = new ArrayList<SearchBook>();
		
		SSPartnerWebService service  = new SSPartnerWebService();
		SSPartnerWebServicePortType client = service.getSSPartnerWebServiceHttpSoap11Endpoint();
		List<SSPartnerBooksListBean> testList = client.getSearchBookList(searchText, false);
		
		for (SSPartnerBooksListBean partnerBook : testList) {
			SearchBook book = new SearchBook();
			book.setIsbn(partnerBook.getIsbn());
			book.setCategoryId(partnerBook.getCategoryIdpr());
			book.setBookTitle(partnerBook.getBookTitle());
			book.setBookAuthor(partnerBook.getBookAuthor());
			book.setBookThumbImage(partnerBook.getThumbImageUrl());
			listBookFinal.add(book);
		}
		
		return listBookFinal;
	}*/

	@Override
	public ArrayList<String> searchText(String searchText) {

		ArrayList<String> predictFinal = new ArrayList<String>();
		ArrayList<String> listSearchByCategory = search
				.predictSearchByCategory(searchText);
		ArrayList<String> listSearchByTitle = search
				.predictSearchByTitle(searchText);
		ArrayList<String> listSearchByAuthor = search
				.predictSearchByAuthor(searchText);
		
		predictFinal.addAll(listSearchByCategory);
		predictFinal.addAll(listSearchByTitle);
		predictFinal.addAll(listSearchByAuthor);
	

		return predictFinal;
	}

	
	
	public ArrayList<SearchBook> SearchByComments(ArrayList<SearchBook> books) {
		String isbn;

		TreeMap<String, Integer> isbn_comments = new TreeMap<String, Integer>();

		ArrayList<SearchBook> temporaryBooks = null;

		int noOfComments;

		for (SearchBook book : books) {
			isbn = book.getIsbn();
			temporaryBooks = search.searchByBookComments(isbn);
			int length = temporaryBooks.size();
			isbn_comments.put(isbn, length);

		}

		List<Entry<String, Integer>> sortedEntries = new ArrayList<Entry<String, Integer>>(
				isbn_comments.entrySet());

		Collections.sort(sortedEntries,
				new Comparator<Entry<String, Integer>>() {

					@Override
					public int compare(Entry<String, Integer> e1,
							Entry<String, Integer> e2) {
						return e2.getValue().compareTo(e1.getValue());
					}
				}
		);

		books.clear();
		int treeIndex = 0;
		for (Entry<String, Integer> arr : sortedEntries) {
			isbn = arr.getKey();
			temporaryBooks =search.searchByISBN(isbn);
			books.add(treeIndex, temporaryBooks.get(0));
			treeIndex++;
		}
		return books;

	}
}
