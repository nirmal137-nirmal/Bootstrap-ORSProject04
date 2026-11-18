package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.UserBean;

import in.co.rays.proj4.model.UserModel;

import in.co.rays.proj4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles user list requests, including loading user records,
 * populating user search criteria, and providing the user list view.
 *
 * @author nirmalfayake
 */
@WebServlet("/ctl/UserListCtl")

public class UserListCtl extends BaseListCtl<UserBean, UserModel> {

	/**
	 * Loads the user list and makes it available to the request.
	 *
	 * @param request the HTTP request
	 */
	@Override

	protected void preload(HttpServletRequest request) {

		UserModel model = new UserModel();

		List<UserBean> userlist = model.search(null, 0, 0);

		request.setAttribute("userList", userlist);

	}

	/**
	 * Populates a UserBean with user search criteria from the HTTP request.
	 *
	 * @param request the HTTP request containing user search parameters
	 * @return the populated UserBean
	 */
	@Override

	protected UserBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		return bean;

	}

	/**
	 * Creates and returns the UserModel instance.
	 *
	 * @return a new UserModel instance
	 */
	@Override

	public UserModel getModel() {

		return new UserModel();

	}

	/**
	 * Returns the JSP view path for the user list page.
	 *
	 * @return the user list view path
	 */
	@Override

	public String getView() {

		return ORSView.USER_LIST_VIEW;

	}

}