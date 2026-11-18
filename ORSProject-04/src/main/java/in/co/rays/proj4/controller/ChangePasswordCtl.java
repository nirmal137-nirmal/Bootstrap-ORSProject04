package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller responsible for handling user password change requests.
 * It validates the input, retrieves the user details, and updates the password.
 *
 * @author Nirmal Fayake
 * 
 * @version 1.0
 */
@WebServlet("/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl<UserBean, UserModel> {

	/**
	 * Validates the login, new password, and old password fields.
	 *
	 * @param request the HTTP request containing user input
	 * @return true if all required fields are valid; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", "newPassword is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", "oldPassword is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a UserBean with login and old password values from the request.
	 *
	 * @param request the HTTP request containing user details
	 * @return the populated UserBean
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));

		return bean;
	}

	/**
	 * Processes the password change request and displays a success or error
	 * message based on the result.
	 *
	 * @param request  the HTTP request containing password details
	 * @param response the HTTP response used to send the result
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		bean = model.changePassword(request.getParameter("newPassword"), bean.getPassword(), bean.getLogin());

		if (bean != null) {
			ServletUtility.setSuccessMessage("your password has been changed check your email", request);
		} else {
			ServletUtility.setErrorMessage("your email is incorrect", request);
		}

		ServletUtility.forward(getView(), request, response);
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
	 * Returns the view associated with the change password operation.
	 *
	 * @return the change password view path
	 */
	@Override
	public String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}