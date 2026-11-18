package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles role-related requests, including validation, data population,
 * and navigation to the role view.
 *
 * @author nirmalfayake
 * @version 1.0
 */
@WebServlet("/ctl/RoleCtl")
public class RoleCtl extends BaseCtl<RoleBean, RoleModel> {

	/**
	 * Preloads role data into the request before displaying the view.
	 *
	 * @param request the HTTP request containing role details
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		RoleModel rmodel = new RoleModel();
		RoleBean bean = populateBean(request);
		//request.setAttribute("roleList", roleList);

		ServletUtility.setBean(bean, request);

	}

	/**
	 * Validates the role name and description submitted by the user.
	 *
	 * @param request the HTTP request containing submitted role details
	 * @return true if all fields are valid; false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "role name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "description is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a RoleBean with role details from the HTTP request.
	 *
	 * @param request the HTTP request containing role details
	 * @return the populated RoleBean
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		RoleBean bean = new RoleBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		return bean;
	}

	/**
	 * Creates and returns the RoleModel instance.
	 *
	 * @return a new RoleModel instance
	 */
	@Override
	public RoleModel getModel() {

		return new RoleModel();
	}

	/**
	 * Returns the JSP view path for role management.
	 *
	 * @return the role view path
	 */
	@Override
	public String getView() {

		return ORSView.ROLE_VIEW;
	}

}