package com.example.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;




public class LoginFilter implements Filter {

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		
		// Setting authentication
		boolean authenticated = false;
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
		// Checking that there is an authentication boolean on session
		if(session.getAttribute("authenticated") != null)
		{
			authenticated = (boolean) session.getAttribute("authenticated");
		}
		
		// Response by the authentication
		if(authenticated) {
			chain.doFilter(req, res);
		}
		else
		{
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendError(HttpStatus.FORBIDDEN.value());
		}
	}

	@Override
	public void destroy() {

	}

}


