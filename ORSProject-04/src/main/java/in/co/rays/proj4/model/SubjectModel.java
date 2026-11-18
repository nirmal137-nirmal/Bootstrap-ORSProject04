package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations for managing subject records, including adding,
 * updating, searching and retrieving subject details.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class SubjectModel extends BaseModel<SubjectBean> {

	private static Logger log = Logger.getLogger(SubjectModel.class);

	/**
	 * Inserts a new subject record into the database after checking for duplicate
	 * subject names.
	 *
	 * @param bean the SubjectBean containing subject details
	 * @return the primary key of the inserted subject record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a subject with the same name already
	 *                                  exists
	 */
	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		log.info("Adding new subject record: " + bean.getName());

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {
			log.error("Subject name already exists: " + bean.getName());
			throw new DuplicateRecordException("Subject name already exist");
		}

		// Foreign key concept
		CourseModel cmodel = new CourseModel();
		CourseBean cbean = cmodel.findByPk(bean.getCourseId());
		// System.out.println("Course Name = " + cbean.getName());

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			// add cbean
			// pstmt.setString(2, bean.getName());
			pstmt.setString(2, cbean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Subject Record Inserted Successfully");

			log.info("Subject record inserted successfully. ID: " + pk);

		} catch (Exception e) {

			log.error("Exception in adding subject record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates an existing subject record after checking for duplicate subject
	 * names.
	 *
	 * @param bean the SubjectBean containing updated subject details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another subject already uses the same
	 *                                  name
	 */
	@Override
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		log.info("Updating subject record. ID: " + bean.getId());

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.error("Subject name already exists: " + bean.getName());
			throw new DuplicateRecordException("name already exist");
		}

		// Foreign key concept
		CourseModel cmodel = new CourseModel();
		CourseBean cbean = cmodel.findByPk(bean.getCourseId());

		// System.out.println("Course name = " + cbean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name=?, description=?, course_id=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			// pstmt.setString(1, bean.getName());
			pstmt.setString(1, cbean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Subject Record Updated Successfully");

			log.info("Subject record updated successfully. ID: " + bean.getId());

		} catch (Exception e) {

			log.error("Exception in updating subject record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied
	 * SubjectBean.
	 *
	 * @param bean the SubjectBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(SubjectBean bean) {

		log.debug("Building WHERE clause for subject search");

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
			if (bean.getCourseId() > 0) {
				sql.append(" and course_id = " + bean.getCourseId());
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

		log.debug("Generated WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a subject record using its name.
	 *
	 * @param name the name of the subject to find
	 * @return the matching SubjectBean, or null if no record is found
	 */
	public SubjectBean findByName(String name) {

		log.info("Finding subject by name: " + name);

		SubjectBean bean = findByUniqueColumn("name", name);
		return bean;

	}

	/**
	 * Returns the database table name used for subject records.
	 *
	 * @return the subject table name
	 */
	@Override
	public String getTable() {

		return "st_subject";
	}

	/**
	 * Creates and returns a new SubjectBean instance.
	 *
	 * @return a new SubjectBean object
	 */
	@Override
	public SubjectBean getBean() {

		return new SubjectBean();
	}
}
