package com.sapestore.vo;

import java.io.Serializable;
import java.util.Date;

public class AddCommentRating implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String userId;
	private String bookComments;
	private Date bookCommentDate;
	private Integer bookRating;
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookComments() {
		return bookComments;
	}
	public void setBookComments(String bookComments) {
		this.bookComments = bookComments;
	}
	public Date getBookCommentDate() {
		return bookCommentDate;
	}
	public void setBookCommentDate(Date bookCommentDate) {
		this.bookCommentDate = bookCommentDate;
	}
	public Integer getBookRating() {
		return bookRating;
	}
	public void setBookRating(Integer bookRating) {
		this.bookRating = bookRating;
	}
	
}
