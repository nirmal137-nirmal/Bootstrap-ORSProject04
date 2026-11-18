package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles student list requests, including populating student search criteria
 * and providing the student list view.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/StudentListCtl")
public class StudentListCtl extends BaseListCtl<StudentBean, StudentModel>{

	/**
	 * Populates a StudentBean with student search criteria from the HTTP request.
	 *
	 * @param request the HTTP request containing student search parameters
	 * @return the populated StudentBean
	 */
	@Override
	protected StudentBean populateBean(HttpServletRequest request) {

		StudentBean bean = new StudentBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		return bean;
	}

	/**
	 * Creates and returns the StudentModel instance.
	 *
	 * @return a new StudentModel instance
	 */
	@Override
	public StudentModel getModel() {

		return new StudentModel();
	}

	/**
	 * Returns the JSP view path for the student list page.
	 *
	 * @return the student list view path
	 */
	@Override
	public String getView() {

		return ORSView.STUDENT_LIST_VIEW;
	}

}