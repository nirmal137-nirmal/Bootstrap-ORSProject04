package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for handling college-related operations such as
 * validating and populating college details.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl<CollegeBean, CollegeModel> {

	/**
	 * Validates the required college fields such as name, address, state, and city.
	 *
	 * @param request the HTTP request containing college details
	 * @return true if all required fields are valid; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "address is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", "state is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", "city is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a CollegeBean with college details received from the request.
	 *
	 * @param request the HTTP request containing college information
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

		populateDTO(bean, request);

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
	 * Returns the view associated with college operations.
	 *
	 * @return the college view path
	 */
	@Override
	public String getView() {

		return ORSView.COLLEGE_VIEW;
	}

}