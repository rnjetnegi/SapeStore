package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sapestore.dao.TransactionHistoryDao;
import com.sapestore.service.TransactionHistoryService;

/**
 * Service  class for Transaction History 
 * @author span42
 *
 */
@Service("transactionService")
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

  @Autowired
    TransactionHistoryDao transaction;

  /*
 * Fetching  the list of order ids for the orders
 * @param userId 
 */
  @Override
    public List fetchPurchasedBookHistory(String userId) {
    List orderInfo = transaction.getUserAllOrders(userId);
    return orderInfo;
  }

}
