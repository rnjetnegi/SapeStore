package com.sapestore.controller;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.OrderService;
import com.sapestore.service.ReportService;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.OrderVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller for admin manage order tab
 * 
 * @author jxu1
 *
 */
@Controller
@SessionAttributes("dispatchList")
public class OrderController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(OrderController.class.getName());

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ReportService reportService;
	private List<DispatchSlip> dispatchSlipBeans;

	public List<DispatchSlip> getDispatchSlipBeans() {
		return dispatchSlipBeans;
	}

	public void setDispatchSlipBeans(List<DispatchSlip> dispatchSlipBeans) {
		this.dispatchSlipBeans = dispatchSlipBeans;
	}

	/**
	 * Redirects to manage orders page @ param modelMap @ return
	 */
	@RequestMapping(value = "/admin/manageOrders", method = RequestMethod.GET)
	public String manageOrders(HttpSession session, ModelMap modelMap,
			HttpServletResponse response) throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug(" OrderController.manageOrders method: START ");
			Map<String, List<OrderVO>> orderMap = orderService.getUndispatchedOrdersList();
			modelMap.addAttribute("toDispatchOrdersList",orderMap);
			modelMap.addAttribute("defaultersList",
					reportService.getFlagedDefaulterslist());

			return "admin/ManageOrders";
		} else {
			return "admin/login";
		}
	}

	/**
	 * Processes the requests for Dispatch Slip. @ param dispatchList @ param
	 * modelMap @ return @ throws Exception
	 */
	@RequestMapping(value = "/admin/dispatchSlip", method = RequestMethod.POST)
	public String dispatchSlip(@RequestParam("dispatchList") int dispatchList,
			ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			List<Integer> list = new ArrayList<Integer>();
			list.add(dispatchList);

			LOGGER.debug(" OrderController.manageOrders method: START ");
			/*
			 * modelMap.addAttribute("orderItemNo", dispatchList);
			 */
			modelMap.addAttribute("dispatchedOrder",
					orderService.getDispatchedOrders(list));

			return "admin/DispatchResult";
		} else {
			return "admin/login";
		}
	}

	/**
	 * Processes payment received @ param dispatchList @ param modelMap @ return @
	 * throws Exception
	 */
	@RequestMapping(value = "/admin/paymentSlip", method = RequestMethod.POST)
	public String paymentSlip(@RequestParam("paymentId") int orderItemId,
			ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			try {
				modelMap.addAttribute("paidOrder",
						orderService.setPaymentReceived(orderItemId));
			} catch (SapeStoreSystemException e) {
				return ApplicationConstants.FAILURE;
			}
			return "admin/PaymentResult";
		} else {
			return "admin/login";
		}
	}

	// Not required at the moment
	// private static List<RentedUpdate> rentedUpdates = new
	// ArrayList<RentedUpdate>();

	/**
	 * Processes rented return (not a requirement at the moment) @ param
	 * rentedUpdateArr @ param modelMap @ return @ throws SapeStoreException
	 */
	/*
	 * @RequestMapping(value = "/updateRent", method = RequestMethod.POST,
	 * params = "return") public String returnOrder(
	 * 
	 * @ModelAttribute("rentedUpdateForm") RentedUpdateForm rentedUpdateArr,
	 * ModelMap modelMap) throws SapeStoreException {
	 * LOGGER.debug("returnOrder method: START"); if (rentedUpdateArr != null) {
	 * List<RentedUpdate> rentedUpdateList = rentedUpdateArr
	 * .getRentedUpdateList(); orderService.updateReturn(rentedUpdateList); }
	 * LOGGER.debug("returnOrder method: END"); return "redirect:/manageOrders";
	 * }
	 */
}