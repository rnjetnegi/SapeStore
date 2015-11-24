package com.sapestore.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapestore.service.TransactionHistoryService;
import com.sapestore.vo.OrderVO;

/**
 * This is a controller class for the transaction history.
 * 
 * @author span42 Date : 10-20-2015
 *
 */

@Controller
public class TransactionHistoryController {
  @Autowired
    private TransactionHistoryService transactionService;
  @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getTransaction() {
    return "Transaction";
  }
  @SuppressWarnings("unchecked")
  /*Processes the Purchased Books Transaction History
     *@Param ModelMap
    @Param session 
    */
  @RequestMapping(value = "/bookspurchased", method = RequestMethod.GET)
    public String getPurchasedBookDetails(ModelMap model, HttpSession session) {
    String userId = (String) session.getAttribute("userLoginName");
    // Adding purchased book list to Model
    List purchasedBookList = new ArrayList();
    List<OrderVO> bookList = transactionService.fetchPurchasedBookHistory(userId);
    if (bookList != null) {
      for (Iterator itr = bookList.iterator(); itr.hasNext();) {
        OrderVO orderItem = (OrderVO) itr.next();
        if (orderItem.getType().equalsIgnoreCase("Purchased")) {
          purchasedBookList.add(orderItem);
        }
      }
    }
    model.addAttribute("purchased", purchasedBookList);
    //model.addAttribute("rented", rentedBookList);
    return "BooksPurchased";
  }
  
    /*Processes the Rented  Books Transaction History
    *@Param ModelMap
    *@Param session 
    */
	
    @RequestMapping(value = "/booksrented", method = RequestMethod.GET)
    public String getRentedBookDetails(ModelMap model, HttpSession session) {
    String userId = (String) session.getAttribute("userLoginName");
    // Adding  book list to Model
    List rentedBookList = new ArrayList();
    List<OrderVO> bookList = transactionService.fetchPurchasedBookHistory(userId);
    if (bookList != null) {
      for (Iterator itr = bookList.iterator(); itr.hasNext();) {
        OrderVO orderItem = (OrderVO) itr.next();
        if (orderItem.getType().equalsIgnoreCase("Rented")) {
          rentedBookList.add(orderItem);
        }
      }
    }
    model.addAttribute("rented", rentedBookList);

    return "BooksRented";
  }
}
