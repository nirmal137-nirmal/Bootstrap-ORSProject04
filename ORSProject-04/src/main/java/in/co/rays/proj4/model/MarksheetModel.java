package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations for managing marksheet records, including
 * adding, updating, searching and retrieving marksheet details.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */

public class MarksheetModel extends BaseModel<MarksheetBean> {

	private static Logger log = Logger.getLogger(MarksheetModel.class);

	/**
	 * Inserts a new marksheet record into the database after checking for duplicate
	 * roll numbers.
	 *
	 * @param bean the MarksheetBean containing marksheet details
	 * @return the primary key of the inserted marksheet record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the roll number already exists
	 */
	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		log.info("Adding new marksheet record. Roll No: " + bean.getRollNo());

		MarksheetBean existBean = findByRollNo(bean.getRollNo());
		if (existBean != null) {
			log.error("Roll number already exists: " + bean.getRollNo());
			throw new DuplicateRecordException("rollNo already exist");
		}

		// Foreign key concept
		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPk(bean.getStudentId());

		// System.out.println("First Name = " + sbean.getFirstName());

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			// pstmt.setString(4, bean.getName());
			// add first name
			pstmt.setString(4, sbean.getFirstName() + " " + sbean.getLastName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Marksheet Record Inserted Successfully");

			log.info("Marksheet record inserted successfully. ID: " + pk);

		} catch (Exception e) {

			log.error("Exception in adding marksheet record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates an existing marksheet record in the database after checking for
	 * duplicate roll numbers.
	 *
	 * @param bean the MarksheetBean containing updated marksheet details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the roll number already exists
	 */
	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		log.info("Updating marksheet record. ID: " + bean.getId());

		MarksheetBean existBean = findByRollNo(bean.getRollNo());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.error("Roll number already exists: " + bean.getRollNo());
			throw new DuplicateRecordException("Rollno already exist");
		}

		// Foreign key concept
		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPk(bean.getStudentId());

		if (sbean == null) {
			log.error("Student not found. Student ID: " + bean.getStudentId());
			throw new ApplicationException("Student not found");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update " + getTable() + " set roll_no=?, student_id=?, name=?, physics=?, chemistry=?, maths=?, "
							+ "created_by=?, modified_by=?, created_datetime=?, modified_datetime=? " + "where id=?");

			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());

			// First Name + Last Name
			pstmt.setString(3, sbean.getFirstName() + " " + sbean.getLastName());

			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());

			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());

			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.setLong(11, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Marksheet Record Updated Successfully");

			log.info("Marksheet record updated successfully. ID: " + bean.getId());

		} catch (Exception e) {

			log.error("Exception in updating marksheet record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied
	 * MarksheetBean.
	 *
	 * @param bean the MarksheetBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(MarksheetBean bean) {

		log.debug("Building WHERE clause for marksheet search");

		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" and roll_no like '" + bean.getRollNo() + "%'");
			}
			if (bean.getStudentId() > 0) {
				sql.append(" and student_id = " + bean.getStudentId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getPhysics() > 0) {
				sql.append(" and physics = " + bean.getPhysics());
			}
			if (bean.getChemistry() > 0) {
				sql.append(" and chemistry = " + bean.getChemistry());
			}
			if (bean.getMaths() > 0) {
				sql.append(" and maths = " + bean.getMaths());
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
	 * Retrieves the top 10 students based on the total marks obtained in Physics,
	 * Chemistry and Mathematics.
	 *
	 * @return a list of MarksheetBean objects containing the merit list
	 * @throws ApplicationException if an error occurs while retrieving the merit
	 *                              list
	 */

	public List<MarksheetBean> getMeritList() throws ApplicationException {

		log.info("Getting marksheet merit list");

		List<MarksheetBean> list = new ArrayList<MarksheetBean>();

		String sql = "SELECT * FROM " + getTable() + " ORDER BY (physics + chemistry + maths) DESC LIMIT 0, 10";

		try {

			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				MarksheetBean bean = new MarksheetBean();

				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));

				list.add(bean);
			}

			rs.close();
			pstmt.close();
			conn.close();

			log.info("Merit list retrieved successfully. Total records: " + list.size());

		} catch (Exception e) {

			log.error("Exception in getting marksheet merit list", e);
			e.printStackTrace();
			throw new ApplicationException("Exception in getting merit list");
		}

		return list;
	}

	/**
	 * Finds a marksheet record using its roll number.
	 *
	 * @param rollNo the roll number of the marksheet to find
	 * @return the matching MarksheetBean, or null if no record is found
	 */
	public MarksheetBean findByRollNo(String rollNo) {

		log.info("Finding marksheet by roll number: " + rollNo);

		MarksheetBean bean = findByUniqueColumn("roll_No", rollNo);
		return bean;

	}

	/**
	 * Returns the database table name used for marksheet records.
	 *
	 * @return the marksheet table name
	 */
	@Override
	public String getTable() {

		return "st_marksheet";
	}

	/**
	 * Creates and returns a new MarksheetBean instance.
	 *
	 * @return a new MarksheetBean object
	 */
	@Override
	public MarksheetBean getBean() {

		return new MarksheetBean();
	}
}
