package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for displaying and searching the list of colleges.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/CollegeListCtl")
public class CollegeListCtl extends BaseListCtl<CollegeBean, CollegeModel> {

	/**
	 * Populates a CollegeBean with college search details received from the request.
	 *
	 * @param request the HTTP request containing college details
	 * @return the populated CollegeBean
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		return bean;
	}

	/**
	 * Creates and returns the CollegeModel instance.
	 *
	 * @return a new CollegeModel instance
	 */
	@Override
	public CollegeModel getModel() {

		return new CollegeModel();
	}

	/**
	 * Returns the view used to display the college list.
	 *
	 * @return the college list view path
	 */
	@Override
	public String getView() {

		return ORSView.COLLEGE_LIST_VIEW;
	}

}