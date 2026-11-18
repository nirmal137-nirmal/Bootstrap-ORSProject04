package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for displaying and searching the list of marksheets.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/MarksheetListCtl")
public class MarksheetListCtl extends BaseListCtl<MarksheetBean, MarksheetModel> {

	/**
	 * Populates a MarksheetBean with marksheet search details received from the request.
	 *
	 * @param request the HTTP request containing marksheet details
	 * @return the populated MarksheetBean
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
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
	 * Returns the view used to display the marksheet list.
	 *
	 * @return the marksheet list view path
	 */
	@Override
	public String getView() {
		return ORSView.MARKSHEET_LIST_VIEW;
	}

}