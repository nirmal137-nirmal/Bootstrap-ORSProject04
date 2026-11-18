package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles role list requests, including populating search criteria
 * and providing the role list view.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends BaseListCtl<RoleBean, RoleModel> {

	/**
	 * Populates a RoleBean with role search criteria from the HTTP request.
	 *
	 * @param request the HTTP request containing role search parameters
	 * @return the populated RoleBean
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

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
	 * Returns the JSP view path for the role list page.
	 *
	 * @return the role list view path
	 */
	@Override
	public String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}

}