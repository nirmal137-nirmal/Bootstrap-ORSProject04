<!DOCTYPE html>

<%@page import="in.co.rays.proj4.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>

<html>

<head>

    <meta charset="UTF-8">

    <title>Change Password</title>

</head>

<body class="bg-light">

    <%
        String succ = ServletUtility.getSuccessMessage(request);

        String error = ServletUtility.getErrorMessage(request);
    %>

    <%@ include file="Header.jsp"%>

    <div class="container py-5">

        <div class="row justify-content-center">

            <div class="col-lg-5 col-md-7 col-sm-10">

                <!-- Change Password Card -->

                <div class="card shadow-lg border-0 rounded-4">

                    <div class="card-body p-4">

                        <!-- Icon -->

                        <div class="text-center mb-3">

                            <i class="bi bi-shield-lock-fill text-primary display-4"></i>

                        </div>

                        <!-- Heading -->

                        <h3 class="text-center fw-bold text-primary mb-1">

                            Change Your Password

                        </h3>

                        <p class="text-center text-muted mb-4">

                            Update your account password

                        </p>


                        <!-- Success Message -->

                        <h3 class="text-success text-center">

                            <%=succ%>

                        </h3>


                        <!-- Error Message -->

                        <h3 class="text-danger text-center">

                            <%=error%>

                        </h3>


                        <form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

                            <!-- Login -->

                            <div class="mb-3">

                                <label class="form-label fw-bold">

                                    <i class="bi bi-person-fill text-primary"></i>

                                    Login

                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-light">

                                        <i class="bi bi-person"></i>

                                    </span>

                                    <input type="text" name="login" value=""
                                        class="form-control"
                                        placeholder="Enter your email">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("login", request)%>

                                </div>

                            </div>


                            <!-- New Password -->

                            <div class="mb-3">

                                <label class="form-label fw-bold">

                                    <i class="bi bi-key-fill text-primary"></i>

                                    New Password

                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-light">

                                        <i class="bi bi-key"></i>

                                    </span>

                                    <input type="password" name="newPassword"
                                        id="newPassword" value=""
                                        class="form-control"
                                        placeholder="Enter your new password">

                                    <button type="button"
                                        class="btn btn-outline-secondary"
                                        onclick="togglePassword('newPassword', 'newEyeIcon')">

                                        <i class="bi bi-eye" id="newEyeIcon"></i>

                                    </button>

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("newPassword", request)%>

                                </div>

                            </div>


                            <!-- Old Password -->

                            <div class="mb-3">

                                <label class="form-label fw-bold">

                                    <i class="bi bi-lock-fill text-primary"></i>

                                    Old Password

                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-light">

                                        <i class="bi bi-lock"></i>

                                    </span>

                                    <input type="password" name="oldPassword"
                                        id="oldPassword" value=""
                                        class="form-control"
                                        placeholder="Enter your old password">

                                    <button type="button"
                                        class="btn btn-outline-secondary"
                                        onclick="togglePassword('oldPassword', 'oldEyeIcon')">

                                        <i class="bi bi-eye" id="oldEyeIcon"></i>

                                    </button>

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("oldPassword", request)%>

                                </div>

                            </div>


                            <!-- Change Password Button -->

                            <div class="d-grid mt-4">

                                <button type="submit" name="operation"
                                    value=<%=ChangePasswordCtl.OP_SAVE%>
                                    class="btn btn-primary btn-lg">

                                    <i class="bi bi-shield-check"></i>

                                    &nbsp; Change Password

                                </button>

                            </div>

                        </form>

                        <!-- Back to Login -->

                        <div class="text-center mt-3">

                            <a href="<%=ORSView.LOGIN_CTL%>"
                                class="text-decoration-none">

                                <i class="bi bi-arrow-left"></i>

                                Back to Login

                            </a>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <%@ include file="Footer.jsp"%>


    <!-- Password Show / Hide -->

    <script>

        function togglePassword(passwordId, eyeIconId) {

            var password = document.getElementById(passwordId);

            var eyeIcon = document.getElementById(eyeIconId);

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