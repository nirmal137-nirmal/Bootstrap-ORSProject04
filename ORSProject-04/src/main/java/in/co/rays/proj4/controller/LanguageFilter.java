package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Filter responsible for handling language selection across the application.
 * It checks the language parameter in the request and updates the application
 * locale accordingly.
 *
 * @author Nirmal Fayake
 * 
 * @version 1.0
 */
@WebFilter("/*")
public class LanguageFilter implements Filter {

	/**
	 * Initializes the language filter.
	 *
	 * @param filterConfig the filter configuration object
	 * @throws ServletException if an error occurs during filter initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * Intercepts incoming requests, checks the selected language, and updates
	 * the application locale when a valid language parameter is provided.
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
		MessageSource messagesource = MessageSource.getInstance();

		String lang = DataUtility.getString(req.getParameter("lang"));
		if (DataValidator.isNotNull(lang)) {
			messagesource.setLocale(lang);
		}

		chain.doFilter(req, response);

	}

	/**
	 * Releases resources held by the language filter.
	 */
	@Override
	public void destroy() {

	}

}