package com.bionic.edu.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Staff.Role;


@WebFilter(urlPatterns = {"/admin/*", "/analytic/*", "/securityOfficer/*", "/accountant/*"},
	initParams = {@WebInitParam(name = "INDEX_PATH", value = "/login.xhtml")})
public class AccessChekerFilter implements Filter {
	
	private String indexPath;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter("INDEX_PATH");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String contextPath = req.getContextPath();
		Staff.Role role = (Role) session.getAttribute("Role");
		
		if (role != null) {
			switch(role) {
				case ADMIN:
					if(req.getRequestURI().equals(contextPath + "/admin/admin.xhtml")) {
						chain.doFilter(req, res);
					} else if (req.getRequestURI().equals(contextPath + "/admin/addFlight.xhtml")) {
						chain.doFilter(req, res);
					} else {
						res.sendRedirect(contextPath + indexPath);
					} break;
				case ACCOUNTANT:
					if(req.getRequestURI().equals(contextPath + "/accountant/accountant.xhtml")) {
						chain.doFilter(req, res);
					} else {
						res.sendRedirect(contextPath + indexPath);
					} break;
				case ANALYTIC:
					if(req.getRequestURI().equals(contextPath + "/analytic/analytic.xhtml")) {
						chain.doFilter(req, res);
					} else {
						res.sendRedirect(contextPath + indexPath);
					} break;
				case SECURITY:
					if(req.getRequestURI().equals(contextPath + "/securityOfficer/securityOfficer.xhtml")) {
						chain.doFilter(req, res);
					} else if (req.getRequestURI().equals(contextPath + "/securityOfficer/addStaff.xhtml")) {
						chain.doFilter(req, res);
					} else {
						res.sendRedirect(contextPath + indexPath);
					} break;
			}
		} else {
			res.sendRedirect(contextPath + indexPath);
		}
	}

	@Override
	public void destroy() {
		
	}
}
