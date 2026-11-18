package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles subject-related requests, including validation, data population,
 * and navigation to the subject view.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet("/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl<SubjectBean, SubjectModel> {

	/**
	 * Validates the subject name and description submitted by the user.
	 *
	 * @param request the HTTP request containing subject details
	 * @return true if all required fields are valid; false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "subject name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "description is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a SubjectBean with subject details from the HTTP request.
	 *
	 * @param request the HTTP request containing subject details
	 * @return the populated SubjectBean
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		SubjectBean bean = new SubjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		populateDTO(bean, request);

		return bean;
	}

	/**
	 * Creates and returns the SubjectModel instance.
	 *
	 * @return a new SubjectModel instance
	 */
	@Override
	public SubjectModel getModel() {

		return new SubjectModel();
	}

	/**
	 * Returns the JSP view path for subject management.
	 *
	 * @return the subject view path
	 */
	@Override
	public String getView() {

		return ORSView.SUBJECT_VIEW;
	}

}