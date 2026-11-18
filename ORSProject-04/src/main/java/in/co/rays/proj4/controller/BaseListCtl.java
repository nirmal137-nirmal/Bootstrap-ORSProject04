package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Abstract controller class that provides common functionality for displaying
 * records in a paginated list.
 *
 * @param <B> the type of bean extending BaseBean
 * @param <M> the type of model extending BaseModel
 * @author Nirmal Fayake
 */
public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel> extends BaseCtl<B, M> {

	/**
	 * Handles HTTP GET requests to retrieve and display the first page of records.
	 * It also retrieves the next page records to determine whether a next page
	 * is available.
	 *
	 * @param request  the HTTP request containing client request data
	 * @param response the HTTP response used to send the result
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		M model = getModel();
		B bean = populateBean(request);
		int pageNo = 1;
		int pageSize = 5;

		List<B> list = model.search(bean, pageNo, pageSize);

		// for next page
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);
		request.setAttribute("nextList", nextList);

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Handles HTTP POST requests for list operations such as delete, next page,
	 * previous page, and search. Retrieves the requested records and forwards
	 * the result to the appropriate view.
	 *
	 * @param request  the HTTP request containing operation and pagination data
	 * @param response the HTTP response used to send the result
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		M model = getModel();
		B bean = populateBean(request);
		int pageNo = 1;
		int pageSize = 5;

		if (OP_DELETE.equalsIgnoreCase(op)) {

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			String[] ids = request.getParameterValues("ids");


			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					model.delete(DataUtility.getInt(id));
					ServletUtility.setSuccessMessage("record deleted successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("select at least one record to delete", request);
			}
		}

		if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo++;
		}

		if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo--;
		}

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		}

		List<B> list = model.search(bean, pageNo, pageSize);

		// Next Page ke liye 
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);
		request.setAttribute("nextList", nextList);


		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		ServletUtility.forward(getView(), request, response);
	}

}