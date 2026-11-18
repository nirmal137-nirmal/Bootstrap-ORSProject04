package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for displaying and searching the list of faculty members.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet("/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseListCtl<FacultyBean, FacultyModel> {

	/**
	 * Populates a FacultyBean with faculty search details received from the request.
	 *
	 * @param request the HTTP request containing faculty details
	 * @return the populated FacultyBean
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

		FacultyBean bean = new FacultyBean();

		//bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		return bean;
	}

	/**
	 * Creates and returns the FacultyModel instance.
	 *
	 * @return a new FacultyModel instance
	 */
	@Override
	public FacultyModel getModel() {

		return new FacultyModel();
	}

	/**
	 * Returns the view used to display the faculty list.
	 *
	 * @return the faculty list view path
	 */
	@Override
	public String getView() {

		return ORSView.FACULTY_LIST_VIEW;
	}

}