<%@page import="in.co.rays.proj4.controller.CollegeCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>College List</title>

</head>

<body class="bg-light">

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<CollegeBean> list = ServletUtility.getList(request);

	List<CollegeBean> nextList = (List<CollegeBean>) request.getAttribute("nextList");

	int pageNo = ServletUtility.getPageNo(request);

	int pageSize = ServletUtility.getPageSize(request);

	int index = (pageNo - 1) * pageSize + 1;

	Iterator<CollegeBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">

		<div class="container py-4">

			<div class="card border-0 shadow-lg rounded-4">

				<div class="card-body p-4">

					<!-- Heading -->

					<div class="text-center mb-4">

						<div class="mb-2">

							<i class="bi bi-building text-primary" style="font-size: 42px;"></i>

						</div>

						<h3 class="fw-bold text-dark mb-0">College List</h3>

					</div>



					<!-- PDF Button - Top Right -->

					<div class="position-absolute top-0 end-0 mt-2 me-3">

						<a href="<%=ORSView.COLLEGE_REPORT_CTL%>?type=pdf" target="_blank"
							class="btn btn-outline-danger btn-sm px-3"> <i
							class="bi bi-file-earmark-pdf me-1"></i> Download PDF
						</a> <a href="<%=ORSView.COLLEGE_REPORT_CTL%>?type=doc"
							target="_blank" class="btn btn-outline-primary btn-sm px-3">
							<i class="bi bi-file-earmark-word me-1"></i> Download DOC
						</a>

					</div>


					<!-- Success Message -->

					<h3 class="text-success text-center">
						<%=succ%>
					</h3>


					<!-- Error Message -->

					<h3 class="text-danger text-center">
						<%=error%>
					</h3>


					<!-- Hidden Field -->

					<input type="hidden" name="pageNo" value="<%=pageNo%>">


					<!-- Search Section -->

					<div class="bg-light rounded-4 p-3 mb-4">

						<div class="row g-3 align-items-center">

							<!-- College Name -->

							<div class="col-md-9">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-building text-primary"></i>

									</span> <input type="text" name="name" value=""
										class="form-control border-start-0"
										placeholder="Search by College Name">

								</div>

							</div>


							<!-- Search Button -->

							<div class="col-md-3">

								<input type="submit" name="operation"
									class="btn btn-primary btn-sm px-4"
									value="<%=CollegeCtl.OP_SEARCH%>">

							</div>

						</div>

					</div>


					<!-- College Table -->

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

									<th>Name</th>

									<th>Address</th>

									<th>State</th>

									<th>City</th>

									<!--  <th>Phone No.</th> -->

									<!-- <th>Id</th> -->

									<th>Edit</th>

								</tr>

							</thead>


							<!-- Table Body -->

							<tbody>

								<%
								while (it.hasNext()) {

									CollegeBean bean = it.next();
								%>

								<tr>

									<!-- Checkbox -->

									<td><input type="checkbox" class="form-check-input"
										name="ids" value="<%=bean.getId()%>"></td>


									<!-- Serial Number -->

									<td><%=index++%></td>


									<!-- Name -->

									<td><%=bean.getName()%></td>


									<!-- Address -->

									<td><%=bean.getAddress()%></td>


									<!-- State -->

									<td><%=bean.getState()%></td>


									<!-- City -->

									<td><%=bean.getCity()%></td>


									<!-- Phone Number -->
									<%-- 
                                    <td>
                                        <%=bean.getPhoneNo()%>
                                    </td>
 --%>

									<!-- ID -->

									<%--  <th>
                                        <%=bean.getId()%>
                                    </th> --%>


									<!-- Edit -->

									<td><a
										href="<%=ORSView.COLLEGE_CTL + "?id=" + bean.getId()%>"
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
								value="<%=CollegeCtl.OP_NEXT%>">

						</div>

					</div>


				</div>

			</div>

		</div>

	</form>

	<%@ include file="Footer.jsp"%>

</body>

</html>