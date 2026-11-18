package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations for managing student records, including adding,
 * updating, searching and retrieving student details.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class StudentModel extends BaseModel<StudentBean> {

	private static Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * Inserts a new student record into the database after checking for duplicate
	 * email addresses.
	 *
	 * @param bean the StudentBean containing student details
	 * @return the primary key of the inserted student record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a student with the same email already
	 *                                  exists
	 */
	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		log.info("Adding new student record: " + bean.getEmail());

		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			log.error("Student email already exists: " + bean.getEmail());
			throw new DuplicateRecordException("email already exist");
		}

		// Foreign key concept

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());

		// System.out.println("college name = " + cbean.getName());

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			// pstmt.setString(3, bean.getCollegeName());
			// Foreign key concept
			pstmt.setString(3, cbean.getName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Student Record Inserted Successfully");

			log.info("Student record inserted successfully. ID: " + pk);

		} catch (Exception e) {

			log.error("Exception in adding student record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates an existing student record after checking for duplicate email
	 * addresses.
	 *
	 * @param bean the StudentBean containing updated student details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another student already uses the same
	 *                                  email
	 */
	@Override
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		log.info("Updating student record. ID: " + bean.getId());

		StudentBean existBean = findByEmail(bean.getEmail());
		if (existBean != null && existBean.getId() != bean.getId()) {
			log.error("Student email already exists: " + bean.getEmail());
			throw new DuplicateRecordException("email already exist");
		}
		// Foreign key concept

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());
		// System.out.println("college name = " + cbean.getName());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set college_id=?, college_name=?, first_name=?, last_name=?, date_of_birth=?, mobile_no=?, email=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, cbean.getName());
			// pstmt.setString(3, bean.getCollegeName());
			// Foreign key concept
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Student Record Updated Successfully");

			log.info("Student record updated successfully. ID: " + bean.getId());

		} catch (Exception e) {

			log.error("Exception in updating student record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied
	 * StudentBean.
	 *
	 * @param bean the StudentBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(StudentBean bean) {

		log.debug("Building WHERE clause for student search");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getCollegeId() > 0) {
				sql.append(" and college_id = " + bean.getCollegeId());
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" and college_name like '" + bean.getCollegeName() + "%'");
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
			if (bean.getDateOfBirth() != null) {
				sql.append(" and date_of_birth '" + bean.getDateOfBirth() + "'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "%'");
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");
			}
			if (bean.getCreatedDatetime() != null) {
				sql.append(" and created_datetime = '" + bean.getCreatedDatetime() + "'");
			}
			if (bean.getModifiedDatetime() != null) {
				sql.append(" and modified_datetime = '" + bean.getModifiedDatetime() + "'");
			}
		}

		log.debug("Generated WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a student record using its email address.
	 *
	 * @param email the email address of the student to find
	 * @return the matching StudentBean, or null if no record is found
	 */
	public StudentBean findByEmail(String email) {

		log.info("Finding student by email: " + email);

		StudentBean bean = findByUniqueColumn("email", email);

		return bean;
	}

	/**
	 * Returns the database table name used for student records.
	 *
	 * @return the student table name
	 */
	@Override
	public String getTable() {

		return "st_student";
	}

	/**
	 * Creates and returns a new StudentBean instance.
	 *
	 * @return a new StudentBean object
	 */
	@Override
	public StudentBean getBean() {

		return new StudentBean();
	}
}
