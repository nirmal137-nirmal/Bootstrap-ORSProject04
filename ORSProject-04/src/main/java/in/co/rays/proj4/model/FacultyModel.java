
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations for managing faculty records, including adding,
 * updating, searching and retrieving faculty details.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class FacultyModel extends BaseModel<FacultyBean> {

	private static Logger log = Logger.getLogger(FacultyModel.class);

	/**
	 * Inserts a new faculty record into the database after checking for duplicate
	 * email addresses.
	 *
	 * @param bean the FacultyBean containing faculty details
	 * @return the primary key of the inserted faculty record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a faculty with the same email already
	 *                                  exists
	 */
	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		log.info("Adding new faculty record: " + bean.getEmail());

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			log.error("Faculty email already exists: " + bean.getEmail());
			throw new DuplicateRecordException("email already exist");
		}

		// Foreign key concept
		CollegeModel fmodel = new CollegeModel();
		CollegeBean fbean = fmodel.findByPk(bean.getCollegeId());

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			// add cbean in this
			// pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(3, fbean.getName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getGender());
			pstmt.setDate(10, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Faculty Record Inserted Successfully");

			log.info("Faculty record inserted successfully. ID: " + pk);

		} catch (Exception e) {

			log.error("Exception in adding faculty record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates an existing faculty record in the database after checking for
	 * duplicate email addresses.
	 *
	 * @param bean the FacultyBean containing updated faculty details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another faculty already uses the same
	 *                                  email
	 */
	@Override
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		log.info("Updating faculty record. ID: " + bean.getId());

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getEmail() != bean.getEmail()) {
			log.error("Faculty email already exists: " + bean.getEmail());
			throw new DuplicateRecordException("email already exist");
		}

		// Foreign key concept
		CollegeModel fmodel = new CollegeModel();
		CollegeBean fbean = fmodel.findByPk(bean.getCollegeId());

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set college_id=?, college_name=?, first_name=?, last_name=?, email=?, mobile_no=?, address=?, gender=?, date_of_birth=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			pstmt.setLong(1, bean.getCollegeId());
			// add cbean
			// pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(2, fbean.getName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getGender());
			pstmt.setDate(9, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("Faculty Record Updated Successfully");

			log.info("Faculty record updated successfully. ID: " + bean.getId());

		} catch (Exception e) {

			log.error("Exception in updating faculty record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied
	 * FacultyBean.
	 *
	 * @param bean the FacultyBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(FacultyBean bean) {

		log.debug("Building WHERE clause for faculty search");

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
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
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
			if (bean.getDateOfBirth() != null) {
				sql.append(" and date_of_birth like '" + bean.getDateOfBirth() + "%'");
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
	 * Finds a faculty record using its email address.
	 *
	 * @param email the email address of the faculty to find
	 * @return the matching FacultyBean, or null if no record is found
	 */
	public FacultyBean findByEmail(String email) {

		log.info("Finding faculty by email: " + email);

		FacultyBean bean = findByUniqueColumn("email", email);

		return bean;
	}

	/**
	 * Returns the database table name used for faculty records.
	 *
	 * @return the faculty table name
	 */
	@Override
	public String getTable() {

		return "st_faculty";
	}

	/**
	 * Creates and returns a new FacultyBean instance.
	 *
	 * @return a new FacultyBean object
	 */
	@Override
	public FacultyBean getBean() {

		return new FacultyBean();
	}
}
