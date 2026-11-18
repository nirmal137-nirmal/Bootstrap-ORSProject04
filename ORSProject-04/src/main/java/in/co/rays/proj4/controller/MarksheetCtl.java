package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for handling marksheet-related operations such as
 * validating and populating student marks details.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	/**
	 * Validates the required marksheet fields, including roll number and subject marks.
	 *
	 * @param request the HTTP request containing marksheet details
	 * @return true if the required fields are valid; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "roll no is required");
			pass = false;
		}

		/*
		 * if (DataValidator.isNull(request.getParameter("name"))) {
		 * request.setAttribute("name", "name is required"); pass = false; }
		 */

		if (!DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", "Enter valid marks");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", "Enter valid marks");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", "Enter valid marks");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a MarksheetBean with marksheet details received from the request.
	 *
	 * @param request the HTTP request containing marksheet information
	 * @return the populated MarksheetBean
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		//bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		return bean;
	}

	/**
	 * Creates and returns the MarksheetModel instance.
	 *
	 * @return a new MarksheetModel instance
	 */
	@Override
	public MarksheetModel getModel() {

		return new MarksheetModel();
	}

	/**
	 * Returns the view associated with marksheet operations.
	 *
	 * @return the marksheet view path
	 */
	@Override
	public String getView() {

		return ORSView.MARKSHEET_VIEW;
	}

}