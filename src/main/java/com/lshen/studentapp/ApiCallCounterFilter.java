package com.lshen.studentapp;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@WebFilter("/*")
public class ApiCallCounterFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		ServletContext ctx = request.getServletContext();
		Integer counter = (Integer) ctx.getAttribute("apiCallCounter");
		counter++;
		ctx.setAttribute("apiCallCounter", counter);
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// Implement init and destroy methods if necessary
}
