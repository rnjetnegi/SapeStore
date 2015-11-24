package com.sapestore.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.sapestore.hibernate.entity.BookRatingComments;

/**
 * Bean class for displaying Book Rating and comments. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class BookRatingCommentsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5252099221385276780L;

	private Integer commentId;	
	
	@NotEmpty
	private String isbn;	
	
	private String userId;
	
	private String bookComments;
	
	private Date bookCommentDate;	
	
	private Double bookRating;
	
	private String date;
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the commentId
	 */
	public Integer getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the bookComments
	 */
	public String getBookComments() {
		return bookComments;
	}

	/**
	 * @param bookComments the bookComments to set
	 */
	public void setBookComments(String bookComments) {
		this.bookComments = bookComments;
	}

	/**
	 * @return the bookCommentDate
	 */
	public Date getBookCommentDate() {
		return bookCommentDate;
	}

	/**
	 * @param bookCommentDate the bookCommentDate to set
	 */
	public void setBookCommentDate(Date bookCommentDate) {
		this.bookCommentDate = bookCommentDate;
	}


	public Double getBookRating() {
		return bookRating;
	}

	public void setBookRating(Double bookRating) {
		this.bookRating = bookRating;
	}

}
