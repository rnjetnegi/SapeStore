/**
 * 
 */
package com.sapestore.controller;

/**
 * @author vmattus
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() {

		return "searchBook";
	}
	
	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchResult() {

		return "../searchResult";
	}

	
}
