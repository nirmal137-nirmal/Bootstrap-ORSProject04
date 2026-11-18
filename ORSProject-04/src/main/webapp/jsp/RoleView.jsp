<!DOCTYPE html>

<%@page import="in.co.rays.proj4.controller.RoleCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<html>

<head>
<meta charset="UTF-8">
<title>Add-Role</title>
</head>

<body class="bg-light">

    <!-- bean ko view pe get kr skte hai -->
    <jsp:useBean id="bean" class="in.co.rays.proj4.bean.RoleBean"
        scope="request">
    </jsp:useBean>

    <%
    String succ = ServletUtility.getSuccessMessage(request);
    String error = ServletUtility.getErrorMessage(request);
    /* bean ko view pe get kr skte hai */
    /* RoleBean bean = (RoleBean) request.getAttribute("bean"); */
    %>

    <%@ include file="Header.jsp"%>

    <form action="<%=ORSView.ROLE_CTL%>" method="post">

        <input type="hidden" name="id"
            value="<%=DataUtility.getStringData(bean.getId())%>">

        <div class="container pt-4 pb-5 mb-5">

            <div class="card border-0 shadow-lg rounded-4 mx-auto"
                style="max-width: 650px;">

                <div class="card-body p-4">

                    <!-- Heading -->
                    <div class="text-center mb-4">

                        <div class="mb-2">
                            <i class="bi bi-person-badge-fill text-primary"
                                style="font-size: 42px;"></i>
                        </div>

                        <h3 class="fw-bold text-dark mb-0">
                            <%=bean != null && bean.getId() > 0 ? "Update Role" : "Add Role"%>
                        </h3>

                    </div>

                    <!-- Success Message -->
                    <h3 class="text-success text-center small">
                        <%=succ%>
                    </h3>

                    <!-- Error Message -->
                    <h3 class="text-danger text-center small">
                        <%=error%>
                    </h3>

                    <div class="bg-light rounded-4 p-3">

                        <!-- Name -->
                        <div class="mb-3">

                            <label class="form-label fw-semibold">
                                Name <span class="text-danger">*</span>
                            </label>

                            <div class="input-group">

                                <span class="input-group-text bg-white">
                                    <i class="bi bi-person-badge text-primary"></i>
                                </span>

                                <input type="text"
                                    class="form-control"
                                    name="name"
                                    value="<%=DataUtility.getStringData(bean.getName())%>"
                                    placeholder="enter role name">

                            </div>

                            <div class="text-danger small mt-1">
                                <%=ServletUtility.getErrorMessage("name", request)%>
                            </div>

                        </div>

                        <!-- Description -->
                        <div class="mb-3">

                            <label class="form-label fw-semibold">
                                Description <span class="text-danger">*</span>
                            </label>

                            <div class="input-group">

                                <span class="input-group-text bg-white">
                                    <i class="bi bi-card-text text-primary"></i>
                                </span>

                                <input type="text"
                                    class="form-control"
                                    name="description"
                                    value="<%=DataUtility.getStringData(bean.getDescription())%>"
                                    placeholder="enter role description">

                            </div>

                            <div class="text-danger small mt-1">
                                <%=ServletUtility.getErrorMessage("description", request)%>
                            </div>

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

        </div>

    </form>

    <%@ include file="Footer.jsp"%>

</body>

</html>