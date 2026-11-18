package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles subject list requests, including populating subject search criteria
 * and providing the subject list view.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/SubjectListCtl")
public class SubjectListCtl extends BaseListCtl<SubjectBean, SubjectModel> {

	/**
	 * Populates a SubjectBean with subject search criteria from the HTTP request.
	 *
	 * @param request the HTTP request containing subject search parameters
	 * @return the populated SubjectBean
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

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
	 * Returns the JSP view path for the subject list page.
	 *
	 * @return the subject list view path
	 */
	@Override
	public String getView() {

		return ORSView.SUBJECT_LIST_VIEW;
	}

}