package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller class responsible for displaying the marksheet merit list.
 * <p>
 * Retrieves the merit list from MarksheetModel and forwards it to the
 * Marksheet Merit List view.
 * </p>
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/ctl/MarksheetMeritListCtl" })
public class MarksheetMeritListCtl extends BaseListCtl<MarksheetBean, MarksheetModel> {

	/**
	 * Handles GET requests to retrieve and display the marksheet merit list.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			List<MarksheetBean> list = getModel().getMeritList();

			request.setAttribute("list", list);

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {

			ServletUtility.handleException(e, request, response);
		}
	}

	/**
	 * Handles POST requests by delegating the request to the doGet method.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Returns the view path of the Marksheet Merit List page.
	 *
	 * @return the Marksheet Merit List view path
	 */
	@Override
	public String getView() {
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

	/**
	 * Returns a new instance of MarksheetModel for database operations.
	 *
	 * @return a new MarksheetModel object
	 */
	@Override
	public MarksheetModel getModel() {
		return new MarksheetModel();
	}
}