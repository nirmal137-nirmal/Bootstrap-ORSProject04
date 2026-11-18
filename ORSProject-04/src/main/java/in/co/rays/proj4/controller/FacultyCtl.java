package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for handling faculty-related operations, including
 * loading college details, validating faculty information, and populating
 * faculty data.
 *
 * @author Nirmal Fayake
 * 
 * @version 1.0
 */
@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	/**
	 * Loads the list of colleges and makes it available to the request.
	 *
	 * @param request the HTTP request used to store the college list
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel cmodel = new CollegeModel();
		List<CollegeBean> CollegeList = cmodel.search(null, 0, 0);
		// request.setAttribute("facultyList", CollegeList);
		ServletUtility.setList(CollegeList, request);
	}

	/**
	 * Validates the required faculty fields, including name, email, mobile number,
	 * address, college, gender, and date of birth.
	 *
	 * @param request the HTTP request containing faculty details
	 * @return true if all fields are valid; otherwise, false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		/*
		 * if (DataValidator.isNull(request.getParameter("collegeName"))) {
		 * request.setAttribute("collegeName", "college name is required"); pass =
		 * false; }
		 */

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "email is required");
			pass = false;

		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", "email is not in valid format");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobileNo is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "address is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "collegeId is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a FacultyBean with faculty details received from the request.
	 *
	 * @param request the HTTP request containing faculty information
	 * @return the populated FacultyBean
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

		FacultyBean bean = new FacultyBean();

		// bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		populateDTO(bean, request);

		return bean;
	}

	/**
	 * Creates and returns the FacultyModel instance.
	 *
	 * @return a new FacultyModel instance
	 */
	@Override
	public FacultyModel getModel() {

		return new FacultyModel();
	}

	/**
	 * Returns the view associated with faculty operations.
	 *
	 * @return the faculty view path
	 */
	@Override
	public String getView() {

		return ORSView.FACULTY_VIEW;
	}

}