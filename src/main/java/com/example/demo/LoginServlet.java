package com.example.demo;




import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.enums.ClientType;

import sidekicks.EnumsConverter;



@Controller
@CrossOrigin(origins = "*")
public class LoginServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CouponSystem cs;
	
/***
 * Login method that takes values from a form
 * and try to make a facade and save it on session attribute
 * @param request
 * @param response
 * @param name
 * @param password
 * @param type
 */
@RequestMapping(value = "/loginController" , method = RequestMethod.POST)	
public @ResponseBody void doPost(HttpServletRequest request, HttpServletResponse response  ,
		                          @RequestParam String name , 
		                          @RequestParam String password , 
		                          @RequestParam String type)
{
	// Boolean for checking authentication
	boolean authenticated = false;
	
	// Type
	ClientType loginType = null;
	
	
	try {
		
		loginType = EnumsConverter.stringToClient(type);
		ClientFacade cf = cs.login(name, password, loginType);
	
		// Checking if can make a facade from parameters and its legal
		if(cf == null){
			try {
				response.sendRedirect("./index.html");
			} catch (IOException e) {
			}
		}
		
		// Setting authentication to true and save facade on the session
		authenticated = true;
		request.getSession().setAttribute("authenticated", authenticated);
		request.getSession().setAttribute("facade", cf);
		System.out.println(request.getSession().getAttribute("facade"));
		
		// Mapping the URLs for send Redirect
		Map<ClientType, String> urls = new HashMap<>();
		urls.put(ClientType.ADMIN, "./Admin/index.html");
		urls.put(ClientType.COMPANY, "./Company/index.html");
		urls.put(ClientType.CUSTOMER, "./Customer/index.html");
		
		
		// Send Redirect
		response.sendRedirect(urls.get(loginType));
		
		
	
	} catch (IOException e) {
	}
}
}
