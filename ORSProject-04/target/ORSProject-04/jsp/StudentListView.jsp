<!DOCTYPE html>

<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.FacultyBean"%>
<%@page import="java.util.List"%>

<html>

<head>
<meta charset="UTF-8">
<title>Student List</title>
</head>

<body class="bg-light">

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<StudentBean> list = ServletUtility.getList(request);
	List<StudentBean> nextList = (List<StudentBean>) request.getAttribute("nextList");

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;

	Iterator<StudentBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">

		<div class="container py-4">

			<div class="card border-0 shadow-lg rounded-4">

				<div class="card-body p-4">

					<!-- Heading -->
					<div class="text-center mb-4">

						<div class="mb-2">
							<i class="bi bi-mortarboard-fill text-primary"
								style="font-size: 42px;"></i>
						</div>

						<h3 class="fw-bold text-dark mb-0">Student List</h3>

					</div>


					<!-- PDF Button - Top Right -->

					<div class="position-absolute top-0 end-0 mt-2 me-3">

						<a href="<%=ORSView.STUDENT_REPORT_CTL%>?type=pdf" target="_blank"
							class="btn btn-outline-danger btn-sm px-3"> <i
							class="bi bi-file-earmark-pdf me-1"></i> Download PDF
						</a> <a href="<%=ORSView.STUDENT_REPORT_CTL%>?type=doc"
							target="_blank" class="btn btn-outline-primary btn-sm px-3">
							<i class="bi bi-file-earmark-word me-1"></i> Download DOC
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

					<!-- Hidden Field -->
					<input type="hidden" name="pageNo" value="<%=pageNo%>">

					<!-- Search Section -->
					<div class="bg-light rounded-4 p-3 mb-4">

						<div class="row g-3 align-items-center">

							<!-- Search First Name -->
							<div class="col-md-5">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-person text-primary"></i>
									</span> <input type="text" name="firstName" value=""
										class="form-control border-start-0"
										placeholder="Search by First Name">

								</div>

							</div>

							<!-- Search Last Name -->
							<div class="col-md-5">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-person text-primary"></i>
									</span> <input type="text" name="lastName" value=""
										class="form-control border-start-0"
										placeholder="Search by Last Name">

								</div>

							</div>

							<!-- Search Button -->
							<div class="col-md-2">

								<input type="submit" name="operation"
									class="btn btn-primary btn-sm px-3"
									value="<%=StudentCtl.OP_SEARCH%>">

							</div>

						</div>

					</div>

					<!-- Student Table -->
					<div class="table-responsive">

						<table
							class="table table-bordered table-hover align-middle text-center mb-0">

							<!-- Table Header -->
							<thead class="table-primary">

								<tr>

									<th><input type="checkbox" class="form-check-input"
										onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">
										Select All</th>

									<th>S No.</th>
									<!--  <th>Id</th> -->
									<th>College Name</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email</th>
									<th>Mobile Number</th>
									<th>College ID</th>
									<th>DOB</th>
									<th>Edit</th>

								</tr>

							</thead>

							<!-- Table Body -->
							<tbody>

								<%
								while (it.hasNext()) {

									StudentBean bean = it.next();
								%>

								<tr>

									<!-- Checkbox -->
									<td><input type="checkbox" class="form-check-input"
										name="ids" value="<%=bean.getId()%>"></td>

									<!-- Serial Number -->
									<td><%=index++%></td>

									<!-- Id -->
									<%--   <td>
                                        <%=bean.getId()%>
                                    </td> --%>

									<!-- College Name -->
									<td><%=bean.getCollegeName()%></td>

									<!-- First Name -->
									<td><%=bean.getFirstName()%></td>

									<!-- Last Name -->
									<td><%=bean.getLastName()%></td>

									<!-- Email -->
									<td><%=bean.getEmail()%></td>

									<!-- Mobile Number -->
									<td><%=bean.getMobileNo()%></td>

									<!-- College ID -->
									<td><%=bean.getCollegeId()%></td>

									<!-- DOB -->
									<td><%=bean.getDateOfBirth()%></td>

									<!-- Edit -->
									<td><a
										href="<%=ORSView.STUDENT_CTL + "?id=" + bean.getId()%>"
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

							<!-- Previous -->
							<input type="submit" name="operation"
								class="btn btn-primary btn-sm px-3"
								<%=pageNo == 1 ? "disabled" : ""%>
								value="<%=BaseCtl.OP_PREVIOUS%>">

							<!-- Delete -->
							<input type="submit" name="operation"
								class="btn btn-danger btn-sm px-3"
								value="<%=BaseCtl.OP_DELETE%>">

							<!-- Next -->
							<input type="submit" name="operation"
								class="btn btn-primary btn-sm px-3"
								<%=nextList.size() == 0 ? "disabled" : ""%>
								value="<%=BaseCtl.OP_NEXT%>">

						</div>

					</div>

				</div>

			</div>

		</div>

	</form>

	<%@ include file="Footer.jsp"%>

</body>

</html>