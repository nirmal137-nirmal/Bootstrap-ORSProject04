package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations for managing role records, including adding,
 * updating, searching and retrieving role details.
 *
 * @author nirmalfayake
 */
public class RoleModel extends BaseModel<RoleBean> {

	private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Inserts a new role record into the database after checking for duplicate role
	 * names.
	 *
	 * @param bean the RoleBean containing role details
	 * @return the primary key of the inserted role record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a role with the same name already exists
	 */
	@Override
	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		log.info("Adding new role record: " + bean.getName());

		RoleBean existBean = findByName(bean.getName());

		if (existBean != null) {
			log.error("Role name already exists: " + bean.getName());
			throw new DuplicateRecordException("role name already exist");
		}
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Being Transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Inserted Successfully");

			log.info("Role record inserted successfully. ID: " + pk);

		} catch (Exception e) {
			log.error("Exception in adding role record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates an existing role record after checking for duplicate role names.
	 *
	 * @param bean the RoleBean containing updated role details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another role already uses the same name
	 */
	@Override
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		log.info("Updating role record. ID: " + bean.getId());

		RoleBean exitsBean = findByName(bean.getName());

		if (exitsBean != null && exitsBean.getId() != bean.getId()) {
			log.error("Role name already exists: " + bean.getName());
			throw new DuplicateRecordException("name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // begin transaction
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, description = ?, created_By = ?, modified_By = ?, created_Datetime = ?, modified_Datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("record updated successfully");

			log.info("Role record updated successfully. ID: " + bean.getId());
		} catch (Exception e) {
			log.error("Exception in updating role record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied
	 * RoleBean.
	 *
	 * @param bean the RoleBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(RoleBean bean) {

		log.debug("Building WHERE clause for role search");

		StringBuffer sql = new StringBuffer("");

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
	 * Finds a role record using its name.
	 *
	 * @param name the name of the role to find
	 * @return the matching RoleBean, or null if no record is found
	 */
	public RoleBean findByName(String name) {

		log.info("Finding role by name: " + name);

		RoleBean bean = findByUniqueColumn("name", name);
		return bean;

	}

	/**
	 * Returns the database table name used for role records.
	 *
	 * @return the role table name
	 */
	@Override
	public String getTable() {

		return "st_role";
	}

	/**
	 * Creates and returns a new RoleBean instance.
	 *
	 * @return a new RoleBean object
	 */
	@Override
	public RoleBean getBean() {

		return new RoleBean();
	}

}
