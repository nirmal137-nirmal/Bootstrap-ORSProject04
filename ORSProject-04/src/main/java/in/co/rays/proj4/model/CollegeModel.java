
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations for managing college records.
 *
 * @author nirmalfayake
 */
public class CollegeModel extends BaseModel<CollegeBean> {

	private static Logger log = Logger.getLogger(CollegeModel.class);

	/**
	 * Inserts a new college record into the database after checking for duplicate names.
	 *
	 * @param bean the CollegeBean containing college details
	 * @return the primary key of the inserted college record
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if a college with the same name already exists
	 */
	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		log.info("Adding new college record: " + bean.getName());

		CollegeBean existbeBean = findByName(bean.getName());
		if(existbeBean != null) {
			log.error("College name already exists: " + bean.getName());
			throw new DuplicateRecordException("name already exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Inserted Successfully");

			log.info("College record inserted successfully. ID: " + pk);

		} catch (Exception e) {
			log.error("Exception in adding college record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	/**
	 * Updates an existing college record after checking for duplicate names.
	 *
	 * @param bean the CollegeBean containing updated college details
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if another college already uses the same name
	 */
	@Override
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		log.info("Updating college record. ID: " + bean.getId());

		CollegeBean existBean = findByName(bean.getName());
		if(existBean != null && existBean.getId() != bean.getId()) {
			log.error("College name already exists: " + bean.getName());
			throw new DuplicateRecordException("name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, address = ?, state = ?, city = ?, phone_no = ?, created_By = ?, modified_By = ?, created_Datetime = ?, modified_Datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Updated Successfully");

			log.info("College record updated successfully. ID: " + bean.getId());

		} catch (Exception e) {
			log.error("Exception in updating college record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied bean.
	 *
	 * @param bean the CollegeBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(CollegeBean bean) {

		log.debug("Building WHERE clause for college search");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" and state like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" and city like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" and phone_no like '" + bean.getPhoneNo() + "%'");
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
	 * Finds a college record by its name.
	 *
	 * @param name the name of the college to find
	 * @return the matching CollegeBean, or null if no record is found
	 */
	public CollegeBean findByName(String name) {

		log.info("Finding college by name: " + name);

		CollegeBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	/**
	 * Returns the database table name used for college records.
	 *
	 * @return the college table name
	 */
	@Override
	public String getTable() {

		return "st_college";
	}

	/**
	 * Creates and returns a new CollegeBean instance.
	 *
	 * @return a new CollegeBean object
	 */
	@Override
	public CollegeBean getBean() {

		return new CollegeBean();
	}

}
