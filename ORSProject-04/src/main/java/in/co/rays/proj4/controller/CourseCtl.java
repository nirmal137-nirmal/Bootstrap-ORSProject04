package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for handling course-related operations such as
 * validating and populating course details.
 *
 * @author Nirmal Fayake
 * 
 * @version 1.0
 */
@WebServlet("/ctl/CourseCtl")
public class CourseCtl extends BaseCtl<CourseBean, CourseModel> {

	/**
	 * Validates the required course fields such as course name, description,
	 * and duration.
	 *
	 * @param request the HTTP request containing course details
	 * @return true if all required fields are valid; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("courseName"))) {
			request.setAttribute("courseName", "Course Name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "Description is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", "Duration is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a CourseBean with course details received from the request.
	 *
	 * @param request the HTTP request containing course information
	 * @return the populated CourseBean
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {

		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("courseName")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		populateDTO(bean, request);

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
	 * Returns the view associated with course operations.
	 *
	 * @return the course view path
	 */
	@Override
	public String getView() {

		return ORSView.COURSE_VIEW;
	}
}
