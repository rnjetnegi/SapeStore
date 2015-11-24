package com.sapestore.entity.test;

import static org.junit.Assert.*;

import com.sapestore.hibernate.entity.Miscelleneous;

import org.junit.Test; 

public class MiscelleneousTest {
  Miscelleneous miscelleneous = new Miscelleneous();

  @Test
  public void testContactUs() {
    String contactUs = "24 *7 customer support hotline number +1 - 2444448080";
    miscelleneous.setContactUs(contactUs);
    String actualResult = miscelleneous.getContactUs();
    assertEquals(contactUs, actualResult);
  }
  
  @Test
  public void testContactUsForNull() {
    String contactUs = null;
    miscelleneous.setContactUs(contactUs);
    String actualResult = miscelleneous.getContactUs();
    assertNull(contactUs, actualResult);
  }

  @Test
  public void testPolicy() {
    String policy = "We value the trust you place in us";
    miscelleneous.setPolicy(policy);
    String actualResult = miscelleneous.getPolicy();
    assertEquals(policy, actualResult);
  }
  
  
  @Test
  public void testPolicyForNull() {
    String policy = null;
    miscelleneous.setPolicy(policy);
    String actualResult = miscelleneous.getPolicy();
    assertNull(policy, actualResult);
  }
  
  


}
