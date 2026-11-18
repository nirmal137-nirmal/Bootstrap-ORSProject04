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
 * Controller responsible for handling forgotten password requests. It validates
 * the user's email address and sends the password to the registered email
 * address.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet("/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl<UserBean, UserModel> {

	/**
	 * Validates the login field and checks whether it contains a valid email
	 * address.
	 *
	 * @param request the HTTP request containing the login details
	 * @return true if the login is valid; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is not in valid formate");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a UserBean with the login value received from the request.
	 *
	 * @param request the HTTP request containing the login details
	 * @return the populated UserBean
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		return bean;
	}

	/**
	 * Processes the forgot password request and displays a success or error message
	 * based on the result.
	 *
	 * @param request  the HTTP request containing the user's login details
	 * @param response the HTTP response used to send the result
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		bean = model.forgotPassword(bean.getLogin());

		if (bean != null) {
			ServletUtility.setSuccessMessage("Password has been sent to your email", request);
		} else {
			ServletUtility.setErrorMessage("Enter valid email ", request);
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
	 * Returns the view associated with the forgot password operation.
	 *
	 * @return the forgot password view path
	 */
	@Override
	public String getView() {
		return ORSView.FORGET_PASSWORD_VIEW;
	}

}