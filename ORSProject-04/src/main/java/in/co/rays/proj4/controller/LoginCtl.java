package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controller responsible for handling user login and logout operations.
 * It validates user credentials, authenticates users, and manages user
 * session information.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl<UserBean, UserModel> {

	/**
	 * Operation identifier used for signing in.
	 */
	public final static String OP_SIGNIN = "SignIn";

	/**
	 * Validates the login and password fields submitted by the user.
	 *
	 * @param request the HTTP request containing login details
	 * @return true if all required fields are provided; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		// Default true kr rkha hai //flag bhi kahete h
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
		}

		return pass;

	}

	/**
	 * Handles GET requests for the login page and performs logout when an
	 * operation parameter is provided.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		if (op != null) {
			HttpSession session = request.getSession();
			ServletUtility.setSuccessMessage("user logout successfully", request);
			session.invalidate();
		}

		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Populates a UserBean with login and password values received from the request.
	 *
	 * @param request the HTTP request containing user credentials
	 * @return the populated UserBean
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		return bean;

	}

	/**
	 * Processes the login request, authenticates the user, and stores user and
	 * role information in the session after successful authentication.
	 *
	 * @param request  the HTTP request containing login details
	 * @param response the HTTP response used to send the result
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		UserBean bean = populateBean(request);
		UserModel model = getModel();
		RoleModel rmodel = new RoleModel();

		HttpSession session = request.getSession();

		if (OP_SIGNIN.equalsIgnoreCase(op)) {

			bean = model.authenticate(bean.getLogin(), bean.getPassword());
			if (bean != null) {
				session.setAttribute("user", bean);
				RoleBean rbean = rmodel.findByPk(bean.getRoleId());
				session.setAttribute("role", rbean.getName());

				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
				return;

			} else {
				ServletUtility.setErrorMessage("Invalid login or password", request);
			}
		}

		// fields already fill request se form ka data lekar Bean object mein set karna.
		bean = populateBean(request);
		ServletUtility.setBean(bean, request);

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
	 * Returns the view associated with the login operation.
	 *
	 * @return the login view path
	 */
	@Override
	public String getView() {
		return ORSView.LOGIN_VIEW;
	}

}