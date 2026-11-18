package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for displaying and searching the list of courses.
 *
 * @author Nirmal Fayake
 * 
 * @version 1.0
 */
@WebServlet("/ctl/CourseListCtl")
public class CourseListCtl extends BaseListCtl<CourseBean, CourseModel> {

	/**
	 * Populates a CourseBean with course search details received from the request.
	 *
	 * @param request the HTTP request containing course details
	 * @return the populated CourseBean
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {

		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("courseName")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		return bean;
	}

	/**
	 * Creates and returns the CourseModel instance.
	 *
	 * @return a new CourseModel instance
	 */
	@Override
	public CourseModel getModel() {

		return new CourseModel();
	}

	/**
	 * Returns the view used to display the course list.
	 *
	 * @return the course list view path
	 */
	@Override
	public String getView() {

		return ORSView.COURSE_LIST_VIEW;
	}

}