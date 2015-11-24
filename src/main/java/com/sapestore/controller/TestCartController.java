package com.sapestore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestCartController {
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void searchBook(
			@RequestParam String isbn,@RequestParam String quantity,HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write(""+1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return;

	}
	
	
	@RequestMapping(value = "/testbuy", method = RequestMethod.POST)
	public void buybook(
			@RequestParam String isbn,@RequestParam String type,HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write(isbn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return;

	}
	
	@RequestMapping(value = "/testrent", method = RequestMethod.GET)
	public void rentbook(
			@RequestParam String isbn,@RequestParam String type,HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return;

	}
	
	@RequestMapping(value = "/testremove", method = RequestMethod.POST)
	public void removebook(
			@RequestParam String isbn,@RequestParam String type,HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("removed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return;

	}

	@RequestMapping(value = "/viewwish", method = RequestMethod.GET)
	public String viewwish(
			HttpServletRequest request, HttpServletResponse response) {
		
		
			
		return "WishList";

	}

}
