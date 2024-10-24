package com.algaworks.estoque.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/*")
public class AuthFilter implements Filter {

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession();
		boolean naoEstaLogado = session.getAttribute("usuarioLogado") == null;
		
		if (naoEstaLogado && !req.getRequestURI().endsWith("/login")) {

			res.sendRedirect("login");
		} else {
			chain.doFilter(request, response);
		}
	}



}
