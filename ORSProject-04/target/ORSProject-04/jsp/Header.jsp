<%@ page import="in.co.rays.proj4.util.MessageSource"%>
<%@ page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page import="in.co.rays.proj4.bean.RoleBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- jQuery UI -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#udatee").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
		});
	});
</script>

<style>
/* Menu text white */
.navbar .nav-link, .navbar .navbar-brand {
	color: #ffffff !important;
}

.navbar .nav-link:hover {
	color: #e6e6e6 !important;
}

/* Right side: all controls same size */
.nav-right .btn, .nav-right .form-select {
	height: 38px !important;
	min-width: 0;
	flex-shrink: 0;
	padding: 0 10px !important;
	display: inline-flex !important;
	flex-direction: row !important;
	align-items: center;
	justify-content: center;
	font-size: 14px !important;
	line-height: 1 !important;
	white-space: nowrap;
	margin: 0 !important;
	border-radius: 6px;
}

.nav-right .btn i {
	display: inline-block !important;
	margin: 0 6px 0 0 !important;
	font-size: 13px !important;
	line-height: 1 !important;
	width: auto !important;
	height: auto !important;
}

.nav-right .form-select {
	background-color: #ffffff;
	color: #000000;
	justify-content: flex-start;
	padding-right: 30px !important;
}

.nav-right .dropdown {
	display: inline-block;
}

.nav-right {
	gap: 6px !important;
	flex-shrink: 0;
}

.nav-right .form-select {
	width: 100px !important;
	padding-right: 24px !important;
}

/* Tighter menu spacing so everything fits */
.navbar .navbar-nav .nav-link {
	padding-left: 6px !important;
	padding-right: 6px !important;
	font-size: 15px;
	white-space: nowrap;
}

.navbar .navbar-brand {
	margin-right: 8px !important;
}

/* Dropdown menus same color as header */
.navbar .dropdown-menu {
	background-color: #4169E1 !important;
	border: 1px solid rgba(255, 255, 255, 0.25);
	box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
}

.navbar .dropdown-menu .dropdown-item {
	color: #ffffff !important;
}

.navbar .dropdown-menu .dropdown-item:hover, .navbar .dropdown-menu .dropdown-item:focus
	{
	background-color: #2949A5 !important;
	color: #ffffff !important;
}

/* Logout turns red on hover */
.nav-right a[href*="operation=logout"]:hover {
	background-color: #dc3545 !important;
	border-color: #dc3545 !important;
	color: #ffffff !important;
}

/* Active menu highlight */
.navbar .nav-link.active-menu {
	font-weight: 600;
	background-color: rgba(255, 255, 255, 0.2);
	border-radius: 6px;
}
</style>

<title>ORS Project</title>

</head>

<body>

	<%
	UserBean userBean = (UserBean) session.getAttribute("user");
	String roleName = (String) session.getAttribute("role");
	MessageSource ms = MessageSource.getInstance();
	boolean isLogin = userBean != null;
	String locale = ms.getLanguage();

	// ================= AUTHORIZATION (role flags) =================
	boolean isAdmin = isLogin && userBean.getRoleId() == RoleBean.ADMIN;
	boolean isStudent = isLogin && userBean.getRoleId() == RoleBean.STUDENT;
	boolean isCollege = isLogin && userBean.getRoleId() == RoleBean.COLLEGE;
	boolean isKiosk = isLogin && userBean.getRoleId() == RoleBean.KIOSK;
	boolean isFaculty = isLogin && userBean.getRoleId() == RoleBean.FACULTY;

	// ---- Per-menu permissions (Add / List) ----
	boolean roleMenu = isAdmin;
	boolean userMenu = isAdmin;
	boolean moduleMenu = isAdmin;

	boolean collegeAdd = isAdmin || isStudent || isKiosk;
	boolean collegeList = isAdmin || isStudent || isKiosk || isFaculty;

	boolean studentAdd = isAdmin || isFaculty || isCollege;
	boolean studentList = isAdmin || isStudent || isFaculty || isCollege;

	boolean marksheetAdd = isAdmin || isFaculty || isCollege;
	boolean marksheetList = isAdmin || isFaculty || isCollege;
	boolean marksheetExtra = isAdmin; // Get Marksheet + Merit List

	boolean courseAdd = isAdmin || isStudent || isKiosk;
	boolean courseList = isAdmin || isStudent || isKiosk || isFaculty || isCollege;

	boolean subjectAdd = isAdmin || isStudent || isFaculty;
	boolean subjectList = isAdmin || isStudent || isFaculty;

	boolean facultyAdd = isAdmin || isStudent;
	boolean facultyList = isAdmin || isStudent || isCollege;
	%>

	<!-- ================= NAVBAR ================= -->

	<nav class="navbar navbar-expand-lg navbar-dark sticky-top"
		style="background-color: #4169E1;">

		<div class="container-fluid">

			<!-- ================= LOGO ================= -->

			<a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>"> <img
				src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="154"
				height="44" alt="ORS Logo" class="rounded">
			</a>


			<!-- ================= MOBILE TOGGLE ================= -->

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">

				<span class="navbar-toggler-icon"></span>

			</button>


			<!-- ================= NAVBAR CONTENT ================= -->

			<div class="collapse navbar-collapse" id="navbarNav">

				<%
				if (isLogin) {
				%>


				<!-- ================= LEFT SIDE MENU ================= -->

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">


					<!-- ================= ROLE (Admin only) ================= -->

					<%
					if (roleMenu) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="roleDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-person-badge"></i> Role
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="roleDropdown">

							<li><a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>">
									<i class="bi bi-plus-circle me-2"></i> Add Role
							</a></li>

							<li><a class="dropdown-item"
								href="<%=ORSView.ROLE_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> Role List
							</a></li>

						</ul></li>
					<%
					}
					%>


					<!-- ================= USER (Admin only) ================= -->

					<%
					if (userMenu) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="userDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-people"></i> User
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="userDropdown">

							<li><a class="dropdown-item" href="<%=ORSView.USER_CTL%>">
									<i class="bi bi-person-plus me-2"></i> Add User
							</a></li>

							<li><a class="dropdown-item"
								href="<%=ORSView.USER_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> User List
							</a></li>

						</ul></li>
					<%
					}
					%>


					<!-- ================= COLLEGE ================= -->

					<%
					if (collegeAdd || collegeList) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="collegeDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-building"></i> College
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="collegeDropdown">

							<%
							if (collegeAdd) {
							%>
							<li><a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>">
									<i class="bi bi-plus-circle me-2"></i> Add College
							</a></li>
							<%
							}
							if (collegeList) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.COLLEGE_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> College List
							</a></li>
							<%
							}
							%>

						</ul></li>
					<%
					}
					%>


					<!-- ================= STUDENT ================= -->

					<%
					if (studentAdd || studentList) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="studentDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-mortarboard"></i> Student
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="studentDropdown">

							<%
							if (studentAdd) {
							%>
							<li><a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>">
									<i class="bi bi-person-plus me-2"></i> Add Student
							</a></li>
							<%
							}
							if (studentList) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.STUDENT_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> Student List
							</a></li>
							<%
							}
							%>

						</ul></li>
					<%
					}
					%>


					<!-- ================= MARKSHEET ================= -->

					<%
					if (marksheetAdd || marksheetList || marksheetExtra) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="marksheetDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-file-earmark-text"></i> Marksheet
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="marksheetDropdown">

							<%
							if (marksheetAdd) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.MARKSHEET_CTL%>"> <i
									class="bi bi-plus-circle me-2"></i> Add Marksheet
							</a></li>
							<%
							}
							if (marksheetList) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.MARKSHEET_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> Marksheet List
							</a></li>
							<%
							}
							if (marksheetExtra) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.GET_MARKSHEET_CTL%>"> <i
									class="bi bi-card-text me-2"></i> Get Marksheet
							</a></li>

							<li><a class="dropdown-item"
								href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"> <i
									class="bi bi-card-text me-2"></i> Merit Marksheet
							</a></li>
							<%
							}
							%>

						</ul></li>
					<%
					}
					%>


					<!-- ================= COURSE ================= -->

					<%
					if (courseAdd || courseList) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="courseDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-book"></i> Course
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="courseDropdown">

							<%
							if (courseAdd) {
							%>
							<li><a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>">
									<i class="bi bi-plus-circle me-2"></i> Add Course
							</a></li>
							<%
							}
							if (courseList) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.COURSE_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> Course List
							</a></li>
							<%
							}
							%>

						</ul></li>
					<%
					}
					%>


					<!-- ================= SUBJECT ================= -->

					<%
					if (subjectAdd || subjectList) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="subjectDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-journal-bookmark"></i> Subject
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="subjectDropdown">

							<%
							if (subjectAdd) {
							%>
							<li><a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>">
									<i class="bi bi-plus-circle me-2"></i> Add Subject
							</a></li>
							<%
							}
							if (subjectList) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.SUBJECT_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> Subject List
							</a></li>
							<%
							}
							%>

						</ul></li>
					<%
					}
					%>


					<!-- ================= FACULTY ================= -->

					<%
					if (facultyAdd || facultyList) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="facultyDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-person-workspace"></i> Faculty
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="facultyDropdown">

							<%
							if (facultyAdd) {
							%>
							<li><a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>">
									<i class="bi bi-person-plus me-2"></i> Add Faculty
							</a></li>
							<%
							}
							if (facultyList) {
							%>
							<li><a class="dropdown-item"
								href="<%=ORSView.FACULTY_LIST_CTL%>"> <i
									class="bi bi-list-ul me-2"></i> Faculty List
							</a></li>
							<%
							}
							%>

						</ul></li>
					<%
					}
					%>


					<!-- ================= MODULE (Admin only) ================= -->

					<%
					if (moduleMenu) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="moduleDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-book"></i> Module
					</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="moduleDropdown">

							<%-- <li><a class="dropdown-item" href="<%=ORSView.VOTER_CTL%>">
									<i class="bi bi-plus-circle me-2"></i> Add Voter
							</a></li> --%>

						</ul></li>
					<%
					}
					%>

				</ul>


				<!-- ================= RIGHT SIDE ================= -->

				<div class="d-flex align-items-center gap-2 nav-right">


					<!-- ================= LANGUAGE ================= -->

					<form class="m-0">

						<select name="lang" onchange="this.form.submit()"
							class="form-select form-select-sm">

							<option value="en" <%=("en".equals(locale)) ? "selected" : ""%>>
								English</option>

							<option value="hi" <%=("hi".equals(locale)) ? "selected" : ""%>>
								Hindi</option>

						</select>

					</form>


					<!-- ================= JAVADOC ================= -->

					<a class="btn btn-outline-light btn-sm px-3"
						href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank"> <i
						class="bi bi-file-earmark-code me-1"></i> JavaDoc

					</a>


					<!-- ================= USER NAME BUTTON ================= -->

					<div class="dropdown">

						<a class="btn btn-outline-light btn-sm px-3 dropdown-toggle"
							href="#" id="profileDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <i
							class="bi bi-person-circle me-1"></i> Hi, <%=userBean.getFirstName()%>

							<%
							if (roleName != null && !roleName.isEmpty()) {
							%> (<%=roleName%>) <%
							}
							%>

						</a>


						<ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end"
							aria-labelledby="profileDropdown">

							<li><a class="dropdown-item"
								href="<%=ORSView.MY_PROFILE_CTL + "?id=" + userBean.getId()%>">
									<i class="bi bi-person-circle me-2"></i> My Profile
							</a></li>

							<li><a class="dropdown-item"
								href="<%=ORSView.CHANGE_PASSWORD_CTL%>"> <i
									class="bi bi-key-fill me-2"></i> Change Password
							</a></li>

						</ul>

					</div>


					<!-- ================= LOGOUT BUTTON ================= -->

					<a class="btn btn-outline-light btn-sm"
						href="<%=ORSView.LOGIN_CTL%>?operation=logout"> <i
						class="bi bi-box-arrow-right me-1"></i> Logout

					</a>

				</div>


				<%
				} else {
				%>


				<!-- ================= GUEST MENU ================= -->

				<ul class="navbar-nav ms-auto align-items-lg-center">

					<li class="nav-item"><a
						class="btn btn-outline-light btn-sm me-2 px-3"
						href="<%=ORSView.WELCOME_CTL%>"> <i
							class="bi bi-house-door me-1"></i> Welcome
					</a></li>

					<li class="nav-item"><a
						class="btn btn-outline-light btn-sm me-2 px-3"
						href="<%=ORSView.LOGIN_CTL%>"> <i
							class="bi bi-box-arrow-in-right me-1"></i> Login
					</a></li>

					<li class="nav-item"><a
						class="btn btn-outline-light btn-sm me-2 px-3"
						href="<%=ORSView.USER_REGISTRATION_CTL%>"> <i
							class="bi bi-person-plus me-1"></i> SignUp
					</a></li>

				</ul>


				<%
				}
				%>

			</div>

		</div>

	</nav>


	<!-- Bootstrap JavaScript -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
		
	</script>

	<!-- Highlight current page menu -->
	<script>
		document.querySelectorAll('#navbarNav .navbar-nav .dropdown-item')
				.forEach(
						function(a) {
							if (a.pathname === window.location.pathname) {
								var toggle = a.closest('.dropdown')
										.querySelector('.nav-link');
								if (toggle) {
									toggle.classList.add('active-menu');
								}
							}
						});
	</script>

</body>

</html>
