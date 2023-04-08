package com.algaworks.warhjr.financeiro.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algaworks.warhjr.financeiro.model.Usuario;

@WebFilter("/financeiro/*")
public class AuthFilter implements Filter {
	
	@Inject
	private Usuario autenticacao;



		@Override
		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
				throws IOException, ServletException {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;
			HttpSession session = (HttpSession) request.getSession();
	
			autenticacao = (Usuario) session.getAttribute("nomeUsuario");

			if ((autenticacao == null) && (!request.getRequestURI().endsWith("login.xhtml")) && (!request.getRequestURI().contains("/javax.faces.resource/"))) {
            //if (autenticacao == null) {
				response.sendRedirect(request.getContextPath() + "/login.xhtml");
				//((HttpServletResponse) response).sendRedirect("login?faces-redirect=true");
			} else {

				chain.doFilter(request, response);
			}


			

			
//			if (!request.getRequestURI().endsWith("/financeiro/login.xhtml")
//					&& !request.getRequestURI().contains("/javax.faces.resource/")) {
//				response.sendRedirect(request.getContextPath() + "/financeiro/login.xhtml");
//			} else {
//				//response.sendRedirect(request.getContextPath() +"./financeiro/public/ConsultaLancamentos.xhtml");
//				chain.doFilter(req, res);
//			}
			
		
//			if (!request.getRequestURI().endsWith("/financeiro/login.xhtml")
//					&& !request.getRequestURI().contains("/javax.faces.resource/")) {
//				response.sendRedirect(request.getContextPath() + "/financeiro/login.xhtml");
//			} else {
				//response.sendRedirect(request.getContextPath() + "/financeiro/public/ConsultaLancamentos.xhtml");
			//	chain.doFilter(req, res);
			//}
			
			
		}



}


