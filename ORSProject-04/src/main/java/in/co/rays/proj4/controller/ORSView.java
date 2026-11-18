package in.co.rays.proj4.controller;

/**
 * Defines application-wide constants for JSP views, servlet controllers,
 * and report file paths used in the ORS Project.
 *
 * @author nirmalfayake
 */
public interface ORSView {

	/** Application context path. */
	public String APP_CONTEXT = "/ORSProject-04";

	/** Folder containing JSP pages. */
	public String PAGE_FOLDER = "/jsp";

	/** Path to the JavaDoc documentation. */
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	/** Path to the error JSP page. */
	public String ERROR_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	/** Path to the marksheet JSP page. */
	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	/** Path to the marksheet list JSP page. */
	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	/** Path to the get marksheet JSP page. */
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";

	/** Path to the user JSP page. */
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	/** Path to the user list JSP page. */
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

	/** Path to the college JSP page. */
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";

	/** Path to the college list JSP page. */
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

	/** Path to the student JSP page. */
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";

	/** Path to the student list JSP page. */
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";

	/** Path to the role JSP page. */
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";

	/** Path to the role list JSP page. */
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";

	/** Path to the course JSP page. */
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";

	/** Path to the course list JSP page. */
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";

	/** Path to the subject JSP page. */
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";

	/** Path to the subject list JSP page. */
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

	/** Path to the user registration JSP page. */
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";

	/** Path to the login JSP page. */
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";

	/** Path to the welcome JSP page. */
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";

	/** Path to the change password JSP page. */
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

	/** Path to the user profile JSP page. */
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";

	/** Path to the forget password JSP page. */
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	/** Path to the marksheet merit list JSP page. */
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	/** Path to the product JSP page. */
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";

	/** Path to the product list JSP page. */
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";

	/** URL mapping for the error controller. */
	public String ERROR_CTL = "/ctl/MarksheetCtl";

	/** URL mapping for the marksheet controller. */
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	/** URL mapping for the marksheet list controller. */
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

	/** URL mapping for the user controller. */
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	/** URL mapping for the user list controller. */
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	/** URL mapping for the college controller. */
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	/** URL mapping for the college list controller. */
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	/** URL mapping for the student controller. */
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	/** URL mapping for the student list controller. */
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	/** URL mapping for the role controller. */
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	/** URL mapping for the role list controller. */
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	/** URL mapping for the course controller. */
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	/** URL mapping for the course list controller. */
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	/** URL mapping for the subject controller. */
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	/** URL mapping for the subject list controller. */
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	/** URL mapping for the user registration controller. */
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	/** URL mapping for the login controller. */
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	/** URL mapping for the welcome controller. */
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	/** URL mapping for the logout controller. */
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";

	/** URL mapping for the get marksheet controller. */
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	/** URL mapping for the change password controller. */
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	/** URL mapping for the user profile controller. */
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	/** URL mapping for the forget password controller. */
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	/** URL mapping for the marksheet merit list controller. */
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	/** URL mapping for the photo upload controller. */
	public String UPLOAD_PHOTO_CTL = APP_CONTEXT + "/ctl/UploadPhotoCtl";

	/** Path to the faculty JSP page. */
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";

	/** Path to the faculty list JSP page. */
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";

	/** URL mapping for the faculty controller. */
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	/** URL mapping for the faculty list controller. */
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	/** URL mapping for the student report controller. */
	public String STUDENT_REPORT_CTL = APP_CONTEXT + "/ctl/StudentReportCtl";

	/** URL mapping for the college report controller. */
	public String COLLEGE_REPORT_CTL = APP_CONTEXT + "/ctl/CollegeReportCtl";

	/** URL mapping for the user report controller. */
	public String USER_REPORT_CTL = APP_CONTEXT + "/ctl/UserReportCtl";

	/** URL mapping for the role report controller. */
	public String ROLE_REPORT_CTL = APP_CONTEXT + "/ctl/RoleReportCtl";

	/** URL mapping for the course report controller. */
	public String COURSE_REPORT_CTL = APP_CONTEXT + "/ctl/CourseReportCtl";

	/** URL mapping for the subject report controller. */
	public String SUBJECT_REPORT_CTL = APP_CONTEXT + "/ctl/SubjectReportCtl";

	/** URL mapping for the marksheet report controller. */
	public String MARKSHEET_REPORT_CTL = APP_CONTEXT + "/ctl/MarksheetReportCtl";

	/** URL mapping for the faculty report controller. */
	public String FACULTY_REPORT_CTL = APP_CONTEXT + "/ctl/FacultyReportCtl";

	/** URL mapping for the product controller. */
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";

	/** URL mapping for the product list controller. */
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";

	/** Path to the course list Jasper report file. */
	public String COURSE_REPORT_VIEW = "/reports/CourseListReport.jrxml";

	/** Path to the student list Jasper report file. */
	public String STUDENT_REPORT_VIEW = "/reports/StudentListReport.jrxml";

	/** Path to the college list Jasper report file. */
	public String COLLEGE_REPORT_VIEW = "/reports/CollegeListReport.jrxml";

	/** Path to the user list Jasper report file. */
	public String USER_REPORT_VIEW = "/reports/UserListReport.jrxml";

	/** Path to the role list Jasper report file. */
	public String ROLE_REPORT_VIEW = "/reports/RoleListReport.jrxml";

	/** Path to the subject list Jasper report file. */
	public String SUBJECT_REPORT_VIEW = "/reports/SubjectListReport.jrxml";

	/** Path to the marksheet list Jasper report file. */
	public String MARKSHEET_REPORT_VIEW = "/reports/MarksheetListReport.jrxml";

	/** Path to the faculty list Jasper report file. */
	public String FACULTY_REPORT_VIEW = "/reports/FacultyListReport.jrxml";

}