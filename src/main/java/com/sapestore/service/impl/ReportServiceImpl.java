package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ReportsDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ReportService;
import com.sapestore.vo.AdminDefaulterReport;
import com.sapestore.vo.ReportVO;

/**
 * Service class for Admin Report functionality.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ReportServiceImpl.class.getName());

	@Autowired
	private ReportsDao reportsDao;

	@Override
	public List<ReportVO> getAdminReport() throws SapeStoreException {
		LOGGER.debug("getAdminReport method: START");
		List<ReportVO> adminReportList = null;
		adminReportList = reportsDao.getAdminReport();
		LOGGER.debug("getAdminReport method: END");

		return adminReportList;
	}

	@Override
	public List<?> getPurchasedRentedAdminReport() throws SapeStoreException {
		LOGGER.debug("getPurchasedRentedAdminReport method: START");
		List<?> adminReportsBeanList = null;
		adminReportsBeanList = reportsDao.getPurchasedRentedAdminReport();
		LOGGER.debug("getPurchasedRentedAdminReport method: END");
		return adminReportsBeanList;
	}

	@Override
	public List<?> getFlagedDefaulterslist() throws SapeStoreException {

		List<String> flagedDefaultersList = new ArrayList();

		List<?> AllDefaultersInfo = reportsDao.getDefaultersAdminReport();

		for (int i = 0; i < AllDefaultersInfo.size(); i++) {

			@SuppressWarnings("unchecked")
			HashMap<String, Object> aRow = (HashMap<String, Object>) AllDefaultersInfo
					.get(i);

			BigDecimal bookRentDecimal = (BigDecimal) aRow.get("RENT_PRICE");
			BigDecimal lateFeeDecimal = (BigDecimal) aRow.get("LATE_FEE");

			int bookRent = bookRentDecimal.intValue();
			int lateFee = lateFeeDecimal.intValue();

			if (lateFee >= bookRent) {
				String userid = (String) aRow.get("USER_ID");
				if (flagedDefaultersList.contains(userid)) {
				} else {
					flagedDefaultersList.add(userid);
				}

			}
		}
		//System.out.println(flagedDefaultersList);

		return flagedDefaultersList;
	}

	@Override
	public List<?> getDefaultersAdminReport() throws SapeStoreException {
		LOGGER.debug("getDefaultersAdminReport method: START");
		List<?> defaulterAdminReportList = null;
		defaulterAdminReportList = reportsDao.getDefaultersAdminReport();
		LOGGER.debug("getDefaultersAdminReport method: END");
		return defaulterAdminReportList;
	}

}