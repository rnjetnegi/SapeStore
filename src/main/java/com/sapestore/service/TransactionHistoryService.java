package com.sapestore.service;

import java.util.List;

/**
 * @author span42
    * Service Interface for Transaction
 *
 */

public interface TransactionHistoryService {
  /*
  * Fetches the order-id list of Purchased books
  * @param userId
  * 
  * */
  public List fetchPurchasedBookHistory(String userId);
}
