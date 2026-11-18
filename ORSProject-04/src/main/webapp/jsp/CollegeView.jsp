<!DOCTYPE html>

<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.CollegeCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>

<head>
<meta charset="UTF-8">
<title>Add College</title>
</head>

<body class="bg-light">

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeBean"
		scope="request">
	</jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<%@ include file="Header.jsp"%>

	<div class="container py-4">

		<div class="card border-0 shadow-lg rounded-4 mx-auto"
			style="max-width: 850px;">

			<div class="card-body p-4">

				<!-- Heading -->

				<div class="text-center mb-4">

					<div class="mb-2">
						<i class="bi bi-building text-primary" style="font-size: 42px;"></i>
					</div>

					<h3 class="fw-bold text-dark mb-0">
						<%=bean != null && bean.getId() > 0 ? "Update College" : "Add College"%>
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
				<!-- College Form -->

				<form action="<%=ORSView.COLLEGE_CTL%>" method="post">

					<input type="hidden" name="id"
						value="<%=DataUtility.getStringData(bean.getId())%>">

					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">

							<!-- College Name -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> College Name <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-building text-primary"></i>
									</span> <input type="text" name="name" class="form-control"
										value="<%=DataUtility.getStringData(bean.getName())%>"
										placeholder="Enter college name">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("name", request)%>
								</div>

							</div>

							<!-- Address -->

							<div class="col-md-12">

								<label class="form-label fw-semibold"> Address <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-geo-alt text-primary"></i>
									</span> <input type="text" name="address" class="form-control"
										value="<%=DataUtility.getStringData(bean.getAddress())%>"
										placeholder="Enter address">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("address", request)%>
								</div>

							</div>

							<!-- State -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> State <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-map text-primary"></i>
									</span> <input type="text" name="state" class="form-control"
										value="<%=DataUtility.getStringData(bean.getState())%>"
										placeholder="Enter state">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("state", request)%>
								</div>

							</div>

							<!-- City -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> City <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-pin-map text-primary"></i>
									</span> <input type="text" name="city" class="form-control"
										value="<%=DataUtility.getStringData(bean.getCity())%>"
										placeholder="Enter city">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("city", request)%>
								</div>

							</div>

							<!-- Save Button -->

							<div class="col-12 text-center mt-3">

								<input type="submit" name="operation"
									value="<%=CollegeCtl.OP_SAVE%>"
									class="btn btn-primary btn-sm px-4">

							</div>

						</div>

					</div>

				</form>

			</div>

		</div>

	</div>

	<%@ include file="Footer.jsp"%>

</body>

</html>