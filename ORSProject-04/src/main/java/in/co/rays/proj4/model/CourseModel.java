package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CourseModel provides database operations and business logic for managing
 * course records, including adding, updating, searching, and filtering courses.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class CourseModel extends BaseModel<CourseBean> {

	private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Adds a new course record to the database.
	 * Checks for duplicate course names before inserting the record.
	 *
	 * @param bean CourseBean containing course details
	 * @return primary key of the inserted course
	 * @throws ApplicationException if a database error occurs
	 * @throws DuplicateRecordException if course name already exists
	 */
	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("name already exits");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			log.info("Record Inserted Successfully");

		} catch (Exception e) {
			log.error("Exception in adding course", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	/**
	 * Updates an existing course record in the database.
	 * Checks for duplicate course names before updating the record.
	 *
	 * @param bean CourseBean containing updated course details
	 * @throws ApplicationException if a database error occurs
	 * @throws DuplicateRecordException if course name already exists
	 */
	@Override
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, description = ?, duration = ?, created_By = ?, modified_By = ?, created_Datetime = ?, modified_Datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			log.info("Record Updated Successfully");

		} catch (Exception e) {
			log.error("Exception in updating course", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Generates the WHERE clause dynamically based on the provided course bean.
	 *
	 * @param bean CourseBean containing search criteria
	 * @return dynamically generated SQL WHERE conditions
	 */
	@Override
	public String getWhereClause(CourseBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");
			}
			if (bean.getCreatedDatetime() != null) {
				sql.append(" and created_datetime like '" + bean.getCreatedDatetime() + "%'");
			}
			if (bean.getModifiedDatetime() != null) {
				sql.append(" and modified_datetime like '" + bean.getModifiedDatetime() + "%'");
			}
		}

		return sql.toString();
	}

	/**
	 * Finds a course record using its unique name.
	 *
	 * @param name course name to search
	 * @return CourseBean if found, otherwise null
	 */
	public CourseBean findByName(String name) {

		CourseBean bean = findByUniqueColumn("name", name);

		return bean;

	}

	/**
	 * Returns the database table name used for course operations.
	 *
	 * @return course table name
	 */
	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_course";
	}

	/**
	 * Creates and returns a new CourseBean object.
	 *
	 * @return new CourseBean instance
	 */
	@Override
	public CourseBean getBean() {
		// TODO Auto-generated method stub
		return new CourseBean();
	}

}