package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.util.ModifyDefaulterInfo;
import com.sapestore.vo.ReportVO;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for Admin report.
 * 
 * CHANGE LOG
 * VERSION DATE AUTHOR MESSAGE 
 * 1.0 20-06-2014 SAPIENT Initial version
 */

@Repository
@Transactional
public class ReportsDao {

	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ReportsDao.class.getName());

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * Method to fetch admin report from the database.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<ReportVO> getAdminReport() throws SapeStoreException {
		LOGGER.debug("getBookDetails method: START");

		try {
			List<Book> bookList = (List<Book>) hibernateTemplate
					.findByNamedQuery("Book.findAllInventory");
			if (!bookList.isEmpty()) {
				return setCategoryDetailBean(bookList);
			} else {
				LOGGER.debug(" There is no book available.");
				return null;
			}
		} catch (SapeStoreSystemException dbe) {
			LOGGER.fatal(
					"A DB exception occured while getting the user profile",
					dbe);
			throw dbe;
		}
	}

	/**
	 * Method to map the values from the map to bean.
	 * 
	 * @param list
	 * @return
	 */
	private List<ReportVO> setCategoryDetailBean(List<Book> bookList) {
		LOGGER.debug(" ProductDao.setCategoryDetailBean method: START ");
		List<ReportVO> finalList = new ArrayList<ReportVO>();
		ReportVO transDO = null;
		for (int i = 0; i < bookList.size(); i++) {
			transDO = new ReportVO();
			transDO.setCategoryName(bookList.get(i).getCategoryName());
			transDO.setBookTitle(bookList.get(i).getBookTitle());
			transDO.setBookAuthor(bookList.get(i).getBookAuthor());
			transDO.setPublisherName(bookList.get(i).getPublisherName());
			transDO.setBookPrice(bookList.get(i).getBookPrice().intValue());
			transDO.setQuantity(bookList.get(i).getQuantity());
			transDO.setCategoryName(bookList.get(i).getCategoryName());
			finalList.add(transDO);
		}
		LOGGER.debug(" ProductDao.setCategoryDetailBean method: END ");
		return finalList;
	}

	/**
	 * Method to fetch admin report from the database for Purchased/Rented
	 * orders.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<?> getPurchasedRentedAdminReport() throws SapeStoreException {
		LOGGER.debug(" ProductDao.getPurchasedRentedAdminReport method: START ");

		List<?> finalList = null;

		try {
			String sqlQuery = "SELECT * FROM ((SELECT * FROM SAPESTORE_BOOKS INNER JOIN (select ORDER_ITEM_INFO.ISBN, ORDER_ITEM_INFO.PURCHASE_TYPE, count(ORDER_ITEM_INFO.ISBN) AS CAL from ORDER_ITEM_INFO group by ORDER_ITEM_INFO.ISBN, ORDER_ITEM_INFO.PURCHASE_TYPE ) ABC ON SAPESTORE_BOOKS.ISBN = ABC.ISBN) XYZ INNER JOIN SAPESTORE_BOOKS_CATEGORIES ON SAPESTORE_BOOKS_CATEGORIES.CATEGORY_ID = XYZ.CATEGORY_ID) ORDER BY XYZ.CATEGORY_ID";
			List<?> OrderedlistBook = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(sqlQuery)
					.setResultTransformer(
							AliasToEntityMapResultTransformer.INSTANCE).list();

			return OrderedlistBook;

		} catch (SapeStoreSystemException se) {
			LOGGER.fatal(
					"A DB exception occured while getting the user profile", se);
		}
		LOGGER.debug(" ProductDao.getPurchasedRentedAdminReport method: END ");
		return finalList;
	}

	/**
	 * Method to fetch admin report from the database for Defaultered orders.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<?> getDefaultersAdminReport() throws SapeStoreException {
		LOGGER.debug("getDefaultersAdminReport method: START");

		List<?> defaulterAdminReportList = null;

		try {

			String sqlQuery = "SELECT * FROM (SELECT * FROM (SELECT * FROM (SELECT o.ORDER_ID , o.ISBN , o.EXPECTED_RETURN_DATE , o.ACTUAL_RETURN_DATE , o.PURCHASE_TYPE , o.RETURN_STATUS FROM ORDER_ITEM_INFO o where o.PURCHASE_TYPE = 'RENTED' and ((o.RETURN_STATUS = 'NOT RETURNED' and o.EXPECTED_RETURN_DATE < SYSDATE) or (o.RETURN_STATUS = 'RETURNED' and o.ACTUAL_RETURN_DATE > o.EXPECTED_RETURN_DATE))) ABC INNER JOIN SAPESTORE_BOOKS ON SAPESTORE_BOOKS.ISBN = ABC.ISBN) XYZ INNER JOIN ORDER_INFO ON ORDER_INFO.ORDER_ID = XYZ.ORDER_ID) TYU INNER JOIN SAPESTORE_MEMBERS_INFO ON SAPESTORE_MEMBERS_INFO.USER_ID = TYU.USER_ID";
			defaulterAdminReportList = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(sqlQuery)
					.setResultTransformer(
							AliasToEntityMapResultTransformer.INSTANCE).list();

		}

		catch (SapeStoreSystemException se) {
			LOGGER.fatal(
					"A DB exception occured while getting the user profile", se);
		}
		ModifyDefaulterInfo modifylist = new ModifyDefaulterInfo();
		defaulterAdminReportList = modifylist.ModifyDefaulterInfornation(defaulterAdminReportList);
		
		return defaulterAdminReportList;
	}


	public Date getDateWithOutTime(Date targetDate) {
		Calendar newDate = Calendar.getInstance();
		newDate.setLenient(false);
		newDate.setTime(targetDate);
		newDate.set(Calendar.HOUR_OF_DAY, 0);
		newDate.set(Calendar.MINUTE, 0);
		newDate.set(Calendar.SECOND, 0);
		newDate.set(Calendar.MILLISECOND, 0);
		return newDate.getTime();
	}

}