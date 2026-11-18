<!DOCTYPE html>

<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.bean.FacultyBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>

<head>

<meta charset="UTF-8">
<title>Add-Faculty</title>

</head>

<body class="bg-light">

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FacultyBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<FacultyBean> facultylist = ServletUtility.getList(request);

	HashMap<String, String> map = new HashMap<String, String>();

	map.put("male", "male");
	map.put("female", "female");
	map.put("other", "other");
	%>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.FACULTY_CTL%>" method="post">

		<input type="hidden" name="id"
			value="<%=DataUtility.getStringData(bean.getId())%>">

		<div class="container pt-4 pb-5 mb-5">

			<div class="card border-0 shadow-lg rounded-4 mx-auto"
				style="max-width: 850px;">

				<div class="card-body p-4">

					<!-- Heading -->

					<div class="text-center mb-4">

						<div class="mb-2">
							<i class="bi bi-person-workspace text-primary"
								style="font-size: 42px;"></i>
						</div>

						<h3 class="fw-bold text-dark mb-0">

							<%=bean != null && bean.getId() > 0 ? "Update Faculty" : "Add Faculty"%>

						</h3>

					</div>

					<!-- Success Message -->
					<%
					if (succ != null && !succ.trim().isEmpty()) {
					%>
					<div class="alert alert-success text-center py-2">
						<i class="bi bi-check-circle-fill me-2"></i>
						<%=succ%>
					</div>
					<%
					}
					%>

					<!-- Error Message -->
					<%
					if (error != null && !error.trim().isEmpty()) {
					%>
					<div class="alert alert-danger text-center py-2">
						<i class="bi bi-exclamation-triangle-fill me-2"></i>
						<%=error%>
					</div>
					<%
					}
					%>

					<div class="bg-light rounded-4 p-3">

						<!-- First Name + Last Name -->

						<div class="row">

							<div class="col-md-6 mb-3">

								<label class="form-label fw-semibold"> First Name <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person-fill text-primary"></i>
									</span> <input type="text" name="firstName"
										value="<%=DataUtility.getStringData(bean.getFirstName())%>"
										placeholder="Enter your first name" class="form-control">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("firstName", request)%>
								</div>

							</div>

							<div class="col-md-6 mb-3">

								<label class="form-label fw-semibold"> Last Name <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person-fill text-primary"></i>
									</span> <input type="text" name="lastName"
										value="<%=DataUtility.getStringData(bean.getLastName())%>"
										placeholder="Enter your last name" class="form-control">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("lastName", request)%>
								</div>

							</div>

						</div>

						<!-- Email + Mobile -->

						<div class="row">

							<div class="col-md-6 mb-3">

								<label class="form-label fw-semibold"> Email <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope-fill text-primary"></i>
									</span> <input type="email" name="email"
										value="<%=DataUtility.getStringData(bean.getEmail())%>"
										placeholder="Enter your email" class="form-control">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("email", request)%>
								</div>

							</div>

							<div class="col-md-6 mb-3">

								<label class="form-label fw-semibold"> Mobile No. <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-phone-fill text-primary"></i>
									</span> <input type="text" name="mobileNo"
										value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
										placeholder="Enter your mobile number" class="form-control">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("mobileNo", request)%>
								</div>

							</div>

						</div>

						<!-- Address -->

						<div class="mb-3">

							<label class="form-label fw-semibold"> Address <span
								class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white"> <i
									class="bi bi-geo-alt-fill text-primary"></i>
								</span> <input type="text" name="address"
									value="<%=DataUtility.getStringData(bean.getAddress())%>"
									placeholder="Enter your address" class="form-control">

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("address", request)%>
							</div>

						</div>

						<!-- College -->

						<div class="mb-3">

							<label class="form-label fw-semibold"> College <span
								class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white"> <i
									class="bi bi-building-fill text-primary"></i>
								</span>

								<%=HTMLUtility.getList("collegeId", DataUtility.getStringData(bean.getCollegeId()), facultylist)%>

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("collegeId", request)%>
							</div>

						</div>

						<!-- Gender + DOB -->

						<div class="row">

							<div class="col-md-6 mb-3">

								<label class="form-label fw-semibold"> Gender <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-gender-ambiguous text-primary"></i>
									</span>

									<%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map)%>

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("gender", request)%>
								</div>

							</div>

							<div class="col-md-6 mb-3">

								<label class="form-label fw-semibold"> DOB <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-calendar-event-fill text-primary"></i>
									</span> <input type="date" name="dob"
										value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>"
										class="form-control">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("dob", request)%>
								</div>

							</div>

						</div>

					</div>

					<!-- Save Button -->

					<div class="col-12 text-center mt-3">

						<input type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
							class="btn btn-primary btn-sm px-4">

					</div>

				</div>

			</div>

		</div>

	</form>

	<%@ include file="Footer.jsp"%>

</body>

</html>