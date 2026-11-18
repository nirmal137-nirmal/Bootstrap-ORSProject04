
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides common database operations for models using generic bean types. The
 * generic type T must extend BaseBean.
 *
 * @author nirmalfayake
 */
public abstract class BaseModel<T extends BaseBean> { // T ka meaning Generic Type hai. BaseModel kisi bhi aise Bean ke
														// saath kaam karega jo BaseBean ko extend karta hai.

	/**
	 * Adds a new record to the database.
	 *
	 * @param bean the bean containing record data
	 * @return the primary key of the newly added record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record is found
	 * 
	 */

	private static Logger log = Logger.getLogger(BaseModel.class);

	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Updates an existing database record.
	 *
	 * @param bean the bean containing updated record data
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record is found
	 */
	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Returns the WHERE clause used to filter database records.
	 *
	 * @param bean the bean containing search criteria
	 * @return the SQL WHERE clause
	 */
	public abstract String getWhereClause(T bean);

	/**
	 * Returns the database table name associated with the model.
	 *
	 * @return the database table name
	 */
	public abstract String getTable();

	/**
	 * Creates and returns a bean instance for the model.
	 *
	 * @return a new bean instance
	 */
	public abstract T getBean();

	/**
	 * Generates the next primary key based on the maximum ID in the table.
	 *
	 * @return the next primary key value
	 * @throws DatabaseException if an error occurs while retrieving the primary key
	 */
	// Find By PK
	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {

			log.info("Getting next primary key for table: " + getTable());

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select MAX(ID) FROM " + getTable());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

			log.info("Next primary key generated: " + (pk + 1));

		} catch (Exception e) {
			log.error("Exception in getting PK", e);
			throw new DatabaseException("Exception: Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}

	/**
	 * Deletes a database record using its primary key.
	 *
	 * @param id the primary key of the record to delete
	 */
	// Delete Method
	public void delete(int id) {
		Connection conn = null;

		try {
			log.info("Deleting record with ID: " + id);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from " + getTable() + " where id = ?");
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			System.out.println("Record Deleted: " + i);
			conn.commit();

			log.info("Record deleted successfully. Total records: " + i);

		} catch (Exception e) {
			log.error("Exception in deleting record with ID: " + id, e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Finds a database record using its primary key.
	 *
	 * @param id the primary key of the record to find
	 * @return the matching bean, or null if no record is found
	 */
	public T findByPk(long id) {

		Connection conn = null;
		T bean = null;

		try {
			log.info("Finding record by primary key: " + id);

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from " + getTable() + " where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

			log.info("FindByPk operation completed for ID: " + id);

		} catch (Exception e) {
			log.error("Exception in finding record by ID: " + id, e);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	/**
	 * Searches database records using the supplied criteria and pagination.
	 *
	 * @param bean     the bean containing search criteria
	 * @param pageNo   the page number to retrieve
	 * @param pageSize the number of records per page
	 * @return a list of matching beans
	 */
	public List<T> search(T bean, int pageNo, int pageSize) {
		Connection conn = null;
		List<T> list = new ArrayList<T>();

		StringBuffer sql = new StringBuffer("select * from " + getTable() + " where 1 = 1");
		sql.append(getWhereClause(bean));

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("Sql ===> " + sql.toString());

		log.debug("Search SQL ===> " + sql.toString());

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
				list.add(bean);
			}

			log.info("Search completed. Total records found: " + list.size());

		} catch (Exception e) {
			log.error("Exception in searching records", e);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	/**
	 * Finds a database record using the value of a unique column.
	 *
	 * @param column the name of the unique database column
	 * @param Value  the value to search for in the specified column
	 * @return the matching bean, or null if no record is found
	 */
	// find By UniqueColumn // Bussiness Validation
	public T findByUniqueColumn(String column, String Value) {
		Connection conn = null;
		T bean = null;

		try {
			log.info("Finding record by unique column: " + column);

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable() + " where " + column + " = ?");

			pstmt.setString(1, Value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

			log.info("FindByUniqueColumn operation completed for column: " + column);

		} catch (Exception e) {
			log.error("Exception in finding record by unique column: " + column, e);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

}
