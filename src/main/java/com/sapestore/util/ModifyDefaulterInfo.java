package com.sapestore.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ModifyDefaulterInfo {
	
	public List<?> ModifyDefaulterInfornation( List<?> defaulterAdminReportList) {
		for (int i = 0; i < defaulterAdminReportList.size(); i++) {

			@SuppressWarnings("unchecked")
			HashMap<String, Object> aRow = (HashMap<String, Object>) defaulterAdminReportList
					.get(i);

			BigDecimal bigDecimal10 = new BigDecimal("10");
			BigDecimal bigDecimal1000 = new BigDecimal("1000");

			Date expectedReturnedDate = (Date) aRow.get("EXPECTED_RETURN_DATE");

			Date actualReturnDate = (Date) aRow.get("ACTUAL_RETURN_DATE");

			if (actualReturnDate == null) {
				Date currentDate = new Date();
				
				BigDecimal diffInDays = new BigDecimal(
						(currentDate.getTime() - expectedReturnedDate.getTime())
								/ (1000 * 60 * 60 * 24));
				aRow.put("LATE_FEE", ((BigDecimal) aRow.get("BOOK_PRICE"))
						.divide(bigDecimal1000).multiply(diffInDays));
				aRow.put("RENT_PRICE", ((BigDecimal) aRow.get("BOOK_PRICE"))
						.divide(bigDecimal10));
			} else {

				BigDecimal diffInDays = new BigDecimal(
						(actualReturnDate.getTime() - expectedReturnedDate
								.getTime()) / (1000 * 60 * 60 * 24));
				aRow.put("LATE_FEE", ((BigDecimal) aRow.get("BOOK_PRICE"))
						.divide(bigDecimal1000).multiply(diffInDays));
				aRow.put("RENT_PRICE", ((BigDecimal) aRow.get("BOOK_PRICE"))
						.divide(bigDecimal10));

			}
			 

		}
		return defaulterAdminReportList;
		
	}

}