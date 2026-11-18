<%@page import="in.co.rays.proj4.model.RoleModel"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.controller.UserListCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>User List</title>

</head>

<body class="bg-light">

	<jsp:useBean id="gbean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<UserBean> nextList = (List<UserBean>) request.getAttribute("nextList");

	List<UserBean> list = ServletUtility.getList(request);

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;

	Iterator<UserBean> it = list.iterator();

	HashMap<String, String> map = new HashMap<String, String>();

	map.put("Male", "Male");
	map.put("Female", "Female");
	map.put("Other", "Other");

	/* For add column Role Name */

	RoleBean rbean = new RoleBean();
	RoleModel rmodel = new RoleModel();
	%>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

		<div class="container py-4">

			<div class="card border-0 shadow-lg rounded-4">

				<div class="card-body p-4">

					<!-- Heading -->

					<div class="text-center mb-4">

						<div class="mb-2">

							<i class="bi bi-people-fill text-primary"
								style="font-size: 42px;"></i>

						</div>

						<h3 class="fw-bold text-dark mb-0">User List</h3>

					</div>



					<!-- PDF Button - Top Right -->

					<div class="position-absolute top-0 end-0 mt-2 me-3">

						<a href="<%=ORSView.USER_REPORT_CTL%>" target="_blank"
							class="btn btn-outline-danger btn-sm px-3"> <i
							class="bi bi-file-earmark-pdf me-1"></i> <%=ms.get("download.pdf")%>

						</a> <a href="<%=ORSView.USER_REPORT_CTL%>?type=doc" target="_blank"
							class="btn btn-outline-primary btn-sm px-3"> <i
							class="bi bi-file-earmark-word me-1"></i> <%=ms.get("download.doc")%>

						</a>

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

					<input type="hidden" name="pageNo" value="<%=pageNo%>">

					<!-- Search Section -->

					<div class="bg-light rounded-4 p-3 mb-4">

						<div class="row g-3 align-items-center">

							<div class="col-md-3">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="firstName" value=""
										class="form-control border-start-0"
										placeholder="Search by First Name">

								</div>

							</div>

							<div class="col-md-3">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="lastName" value=""
										class="form-control border-start-0"
										placeholder="Search by Last Name">

								</div>

							</div>

							<div class="col-md-3">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="email" name="login" value=""
										class="form-control border-start-0"
										placeholder="Search by Email">

								</div>

							</div>

							<div class="col-md-2">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-gender-ambiguous text-primary"></i>

									</span>

									<%=HTMLUtility.getList("gender", DataUtility.getStringData(gbean.getGender()), map)%>

								</div>

							</div>

							<div class="col-md-1">

								<input type="submit" name="operation"
									class="btn btn-primary btn-sm px-3 w-100"
									value="<%=UserCtl.OP_SEARCH%>">

							</div>

						</div>

					</div>

					<!-- User Table -->

					<div class="table-responsive">

						<table
							class="table table-bordered table-hover align-middle text-center mb-0">

							<thead class="table-primary">

								<tr>

									<th><input type="checkbox" class="form-check-input"
										id="selectAll"
										onclick="document.querySelectorAll('input[name=ids]').forEach(c => c.checked = this.checked)">

										Select All</th>

									<th>S No.</th>

									<!-- <th>Id</th> -->

									<th>Profile</th>

									<th>First Name</th>

									<th>Last Name</th>

									<th>Login</th>

									<th>DOB</th>

									<!-- <th>Role ID</th> -->

									<th>Role Name</th>

									<th>Gender</th>

									<th>Edit</th>

								</tr>

							</thead>

							<tbody>

								<%
								while (it.hasNext()) {

									UserBean bean = it.next();

									rbean = rmodel.findByPk(bean.getRoleId());
								%>

								<tr>

									<td><input type="checkbox" class="form-check-input"
										name="ids" value="<%=bean.getId()%>"
										onclick="document.getElementById('selectAll').checked = document.querySelectorAll('input[name=ids]:checked').length === document.querySelectorAll('input[name=ids]').length">

									</td>

									<td><%=index++%></td>

									<%-- <td><%=bean.getId()%></td> --%>

									<td><img
										src="<%=ORSView.UPLOAD_PHOTO_CTL + "?id=" + bean.getId()%>"
										width="50" height="50" onerror="this.style.display='none';"
										alt="User Photo" class="rounded-circle border"
										style="object-fit: cover;"></td>

									<%-- <th><%=rbean.get%></th> --%>

									<td><%=bean.getFirstName()%></td>

									<td><%=bean.getLastName()%></td>

									<td><%=bean.getLogin()%></td>

									<td><%=bean.getDob()%></td>

									<%-- <td><%=bean.getRoleId()%></td> --%>

									<td><%=rbean.getName()%></td>

									<td><%=bean.getGender()%></td>

									<td><a
										href="<%=ORSView.USER_CTL + "?id=" + bean.getId()%>"
										class="btn btn-sm btn-outline-primary"> <i
											class="bi bi-pencil-square me-1"></i> Edit

									</a></td>

								</tr>

								<%
								}
								%>

							</tbody>

						</table>

					</div>

					<!-- Pagination / Actions -->

					<div class="border-top mt-4 pt-3 pb-5">

						<div
							class="d-flex justify-content-center align-items-center gap-2">

							<input type="submit" name="operation"
								class="btn btn-primary btn-sm px-3"
								<%=pageNo == 1 ? "disabled" : ""%>
								value="<%=BaseCtl.OP_PREVIOUS%>"> <input type="submit"
								name="operation" class="btn btn-danger btn-sm px-3"
								value="<%=BaseCtl.OP_DELETE%>"> <input type="submit"
								name="operation" class="btn btn-primary btn-sm px-3"
								<%=nextList.size() == 0 ? "disabled" : ""%>
								value="<%=UserListCtl.OP_NEXT%>">

						</div>

					</div>

				</div>

			</div>

		</div>

	</form>

	<%-- 
    <div align="center">

        <!-- View pe page No or Page Size Dikhane ke liye -->

        <h2><%="pageNo = " + pageNo + "|" + "pageSize = " + list.size()%></h2>

    </div> 
    --%>

	<%@ include file="Footer.jsp"%>

</body>

</html>