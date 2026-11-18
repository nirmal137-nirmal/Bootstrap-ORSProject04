package in.co.rays.proj4.controller;

import in.co.rays.proj4.model.BaseModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * Handles requests for the welcome page.
 *
 * @author nirmalfayake
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	/**
	 * Returns the model associated with this controller.
	 *
	 * @return null as no model is required for the welcome page
	 */
	@Override
	public BaseModel getModel() {

		return null;
	}

	/**
	 * Returns the view path for the welcome page.
	 *
	 * @return the welcome page view path
	 */
	@Override
	public String getView() {

		return ORSView.WELCOME_VIEW;
	}

}