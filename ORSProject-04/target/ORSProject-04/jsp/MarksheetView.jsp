<!DOCTYPE html>

<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>

<head>

<meta charset="UTF-8">

<title>Add Marksheet</title>

</head>

<body class="bg-light">

	<jsp:useBean id="bean"
		class="in.co.rays.proj4.bean.MarksheetBean"
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

						<i class="bi bi-journal-check text-primary"
							style="font-size: 42px;"></i>

					</div>

					<h3 class="fw-bold text-dark mb-0">

						<%=bean != null && bean.getId() > 0
								? "Update Marksheet"
								: "Add Marksheet"%>

					</h3>

				</div>


				<!-- Success Message -->

				<%
				if (succ != null && !succ.trim().isEmpty()) {
				%>

				<div class="alert alert-success text-center py-2">

					<i class="bi bi-check-circle me-1"></i>

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

					<i class="bi bi-exclamation-circle me-1"></i>

					<%=error%>

				</div>

				<%
				}
				%>


				<!-- Marksheet Form -->

				<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

					<!-- Hidden ID -->

					<input type="hidden" name="id"
						value="<%=DataUtility.getStringData(bean.getId())%>">


					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">

							<!-- Roll Number -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">

									Roll No
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-hash text-primary"></i>

									</span>

									<input type="text"
										name="rollNo"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getRollNo())%>"
										placeholder="Enter your roll no">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("rollNo", request)%>

								</div>

							</div>


							<!-- Student ID -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">

									Student ID

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-person text-primary"></i>

									</span>

									<input type="number"
										name="studentId"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getStudentId())%>"
										placeholder="Enter existing Student ID">

								</div>

							</div>


							<%-- 
							<div class="col-md-6">

								<label class="form-label fw-semibold">

									Name
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-person text-primary"></i>

									</span>

									<input type="text"
										name="name"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getName())%>"
										placeholder="Enter your name">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("name", request)%>

								</div>

							</div>
							--%>


							<!-- Physics -->

							<div class="col-md-4">

								<label class="form-label fw-semibold">

									Physics
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-book text-primary"></i>

									</span>

									<input type="text"
										name="physics"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getPhysics())%>"
										placeholder="Enter physics marks">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("physics", request)%>

								</div>

							</div>


							<!-- Chemistry -->

							<div class="col-md-4">

								<label class="form-label fw-semibold">

									Chemistry
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-book text-primary"></i>

									</span>

									<input type="text"
										name="chemistry"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getChemistry())%>"
										placeholder="Enter chemistry marks">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("chemistry", request)%>

								</div>

							</div>


							<!-- Maths -->

							<div class="col-md-4">

								<label class="form-label fw-semibold">

									Maths
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-calculator text-primary"></i>

									</span>

									<input type="text"
										name="maths"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getMaths())%>"
										placeholder="Enter maths marks">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("maths", request)%>

								</div>

							</div>


							<!-- Save / Update Button -->

							<div class="col-12 text-center mt-3">

								<input type="submit"
									name="operation"
									value="<%=BaseCtl.OP_SAVE%>"
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