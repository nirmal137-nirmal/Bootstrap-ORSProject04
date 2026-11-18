package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller class responsible for retrieving a marksheet based on the
 * student's roll number.
 * <p>
 * It validates the roll number, fetches the marksheet details from the
 * database using MarksheetModel, and forwards the request to the
 * Get Marksheet view with the appropriate success or error message.
 * </p>
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet("/ctl/GetMarksheetCtl")
public class GetMarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	/**
	 * Handles GET requests and forwards the request to the Get Marksheet view.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles POST requests to retrieve a marksheet using the given roll number.
	 * <p>
	 * Validates the roll number and searches for the corresponding marksheet.
	 * If found, sets the marksheet bean in the request; otherwise, displays
	 * an appropriate error message.
	 * </p>
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rollNo = DataUtility.getString(request.getParameter("rollNo"));

		if (rollNo == null || rollNo.trim().isEmpty()) {
			ServletUtility.setErrorMessage("Roll No is required", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		MarksheetBean bean = getModel().findByRollNo(rollNo);

		if (bean == null) {
			ServletUtility.setErrorMessage("Marksheet not found for Roll No : " + rollNo, request);
		} else {
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view name of the Get Marksheet page.
	 *
	 * @return the Get Marksheet view path
	 */
	@Override
	public String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
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