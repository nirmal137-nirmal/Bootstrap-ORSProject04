package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Front controller filter that checks whether a user is logged in before
 * allowing access to protected application resources.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */

// FrontController design pattern is used to prevent/stop any user to access application without login

@WebFilter("/ctl/*")
public class FrontCtl implements Filter {

	/**
	 * Initializes the filter.
	 *
	 * @param filterConfig the filter configuration object
	 * @throws ServletException if an error occurs during filter initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * Intercepts incoming requests and checks whether the user session exists.
	 * If the user is not logged in, it forwards the request to the login view.
	 * Otherwise, it allows the request to continue through the filter chain.
	 *
	 * @param request  the incoming servlet request
	 * @param response the servlet response
	 * @param chain    the filter chain used to pass the request to the next component
	 * @throws IOException      if an input or output error occurs
	 * @throws ServletException if a servlet-related error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {
			ServletUtility.setErrorMessage("session has been expired please re-login", req);
			ServletUtility.forward(ORSView.LOGIN_VIEW, req, res);
			return;
		}
		chain.doFilter(request, response); // call next config filter/controller in the chain
	}

	/**
	 * Releases resources held by the filter.
	 */
	@Override
	public void destroy() {

	}
}