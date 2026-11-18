<!DOCTYPE html>

<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.MessageSource"%>

<html>

<head>

<meta charset="UTF-8">

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

<title>Login</title>

</head>

<body class="bg-light">


	<%@ include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<!-- Login Card -->

	<div class="container py-5">

		<div class="row justify-content-center">

			<div class="col-lg-5 col-md-7 col-sm-10">

				<div class="card shadow-lg border-0 rounded-4">

					<div class="card-body p-4">

						<!-- ORS Icon -->

						<div class="text-center mb-3">

							<i class="bi bi-mortarboard-fill text-primary display-4"></i>

						</div>

						<!-- Heading -->

						<h3 class="text-center fw-bold text-primary mb-4">
							<%=ms.get("login.title")%>
						</h3>

						<!-- Success Message -->

						<%
						if (succ != null && !succ.isEmpty()) {
						%>

						<div
							class="alert alert-success alert-dismissible fade show d-flex align-items-center">

							<i class="bi bi-check-circle-fill me-2"></i>

							<%=succ%>

							<button type="button" class="btn-close"
								data-bs-dismiss="alert" aria-label="Close"></button>

						</div>

						<%
						}
						%>

						<!-- Error Message -->

						<%
						if (error != null && !error.isEmpty()) {
						%>

						<div
							class="alert alert-danger alert-dismissible fade show d-flex align-items-center"
							role="alert">

							<i class="bi bi-x-circle-fill me-2"></i>

							<div>
								<%=error%>
							</div>

							<button type="button" class="btn-close"
								data-bs-dismiss="alert" aria-label="Close"></button>

						</div>

						<%
						}
						%>

						<form action="<%=ORSView.LOGIN_CTL%>" method="post">

							<!-- Login -->

							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-person-fill text-primary"></i>

									<%=ms.get("login.email")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">
										<i class="bi bi-person"></i>
									</span>

									<input type="text" name="login"
										class="form-control"
										placeholder="enter you login"
										value="<%=DataUtility.getStringData(bean.getLogin())%>">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("login", request)%>

								</div>

							</div>

							<!-- Password -->

							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-lock-fill text-primary"></i>

									<%=ms.get("login.password")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">
										<i class="bi bi-lock"></i>
									</span>

									<input type="password" name="password"
										id="password"
										value="<%=DataUtility.getStringData(bean.getPassword())%>"
										class="form-control"
										placeholder="enter you password">

									<button type="button"
										class="btn btn-outline-secondary"
										onclick="togglePassword()">

										<i class="bi bi-eye" id="eyeIcon"></i>

									</button>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("password", request)%>

								</div>

							</div>

							<!-- Login Button -->

							<div class="d-grid mt-4">

								<button type="submit" name="operation"
									value="<%=LoginCtl.OP_SIGNIN%>"
									class="btn btn-primary btn-lg">

									<i class="bi bi-box-arrow-in-right"></i> &nbsp;
									Login

								</button>

							</div>

							<!-- Forgot Password -->

							<div class="text-center mt-3">

								<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"
									class="text-decoration-none text-primary">

									<i class="bi bi-key-fill"></i>

									<%=ms.get("login.forgotpassword")%>

								</a>

							</div>

						</form>

					</div>

				</div>

			</div>

		</div>

	</div>

	<%@include file="Footer.jsp"%>

	<!-- Bootstrap JavaScript -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	</script>

	<!-- Password Show / Hide -->

	<script>

	function togglePassword() {

		var password = document.getElementById("password");

		var eyeIcon = document.getElementById("eyeIcon");

		if (password.type === "password") {

			password.type = "text";

			eyeIcon.classList.remove("bi-eye");

			eyeIcon.classList.add("bi-eye-slash");

		} else {

			password.type = "password";

			eyeIcon.classList.remove("bi-eye-slash");

			eyeIcon.classList.add("bi-eye");

		}

	}

	</script>

</body>

</html>