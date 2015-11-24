package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ReportService;
import com.sapestore.service.impl.ReportServiceImpl;
import com.sapestore.vo.ReportVO;

/**
 * This is a controller class for the admin report.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.1 28-10-2015 SIDDHARTH Initial
 * version with little modification
 */

@Controller
public class ReportsController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ReportsController.class.getName());

	@Autowired
	private ReportService reportService;

	private List<ReportVO> adminReportList;

	public List<ReportVO> getAdminReportList() {
		return adminReportList;
	}

	public void setAdminReportList(List<ReportVO> adminReportList) {
		this.adminReportList = adminReportList;
	}

	/**
	 * Processes the requests for admin report.
	 * 
	 * @param modelMap
	 * @param response 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminReport", method = RequestMethod.GET)
	public String adminReport(ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("adminReport method: START");
			this.setAdminReportList(reportService.getAdminReport());
			LOGGER.debug("adminReport method: END");
			return "admin/AdminHome";
		} else {
			return "admin/login";
		}
	}

	/**
	 * Processes the requests for defaulters report.
	 * 
	 * @param modelMap
	 * @param response 
	 * @return
	 */
	@RequestMapping(value = "/admin/defaultersReport", method = RequestMethod.GET)
	public String defaultersReport(ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("defaultersReport method: START");

			modelMap.addAttribute("defaultersReportsList",
					reportService.getDefaultersAdminReport());
			LOGGER.debug("defaultersReport method: END");
			return "admin/DefaulterReport";
		} else {
			return "admin/login";
		}

	}

	/**
	 * Processes the Purchased/Rented report requests.
	 * 
	 * @param modelMap
	 * @param response 
	 * @return
	 */
	@RequestMapping(value = "/admin/purchasedRentedReport", method = RequestMethod.GET)
	public String purchasedRentedReport(ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("purchasedRentedReport method: START");
			modelMap.addAttribute("adminReportsList",
					reportService.getPurchasedRentedAdminReport());
			LOGGER.debug("purchasedRentedReport method: END");
			return "admin/PurchasedRentedReport";
		} else {
			return "admin/login";
		}
	}
}