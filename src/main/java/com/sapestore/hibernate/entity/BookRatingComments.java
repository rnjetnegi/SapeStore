package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="BOOK_RATING_COMMENTS")
@NamedQueries(value = {
		@NamedQuery(name = "BookRatingComments.findByCommentId", query = "from BookRatingComments b where b.commentId = :commentId"),
		@NamedQuery(name = "BookRatingComments.findByIsbn",query = "from BookRatingComments br where br.isbn = :isbn order by br.bookCommentDate desc"),
		@NamedQuery(name = "BookRatingComments.findByIsbnForRatings",query = "from BookRatingComments br where br.isbn = :isbn order by br.bookRating desc"),
		@NamedQuery(name = "BookRatingComments.findByIsbnAnduserId",query="from BookRatingComments br where br.isbn = :isbn and br.userId=:userId")
		})
public class BookRatingComments implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5605441280823670283L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTSEQUENCE")
	@SequenceGenerator(name = "COMMENTSEQUENCE", sequenceName = "COMMENTSEQUENCE", allocationSize = 1)
	@Column(name="COMMENT_ID")
	private Integer commentId;	
	
	@Column(name="ISBN")
	private String isbn;	
	
	@Column(name="USER_ID")
	private String userId;
		
	@Column(name="BOOK_COMMENTS")
	private String bookComments;
	
	@Column(name="BOOK_COMMENT_DATE")
	private Date bookCommentDate;	
	
	@Column(name="BOOK_RATING")
	private Double bookRating;

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
