package com.vois.vtv_cts.filter;

import java.io.IOException;

import javax.servlet.http.HttpFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class JwtRequestFilter extends HttpFilter{

	private static final long serialVersionUID = 6250846013611484440L;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Request coming to filter....!!!!!!!");	
		if(request.getHeader("typ").equalsIgnoreCase("JWT")) {
			
		}else {
			System.out.println("Unable to get JWT Token");
		}
		chain.doFilter(request, response);
		
	}
}
