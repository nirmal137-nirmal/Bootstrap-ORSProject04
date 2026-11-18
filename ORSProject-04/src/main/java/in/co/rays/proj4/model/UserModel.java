package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Provides database operations and business logic for managing user records,
 * including registration, authentication, password management and photo
 * updates.
 *
 * @author nirmalfayake
 */
public class UserModel extends BaseModel<UserBean> {

	private static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Inserts a new user record into the database after checking for duplicate
	 * login credentials.
	 *
	 * @param bean the UserBean containing user details
	 * @return the primary key of the inserted user record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the login already exists
	 */
	@Override
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		log.info("Adding new user record: " + bean.getLogin());

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			log.error("User login already exists: " + bean.getLogin());
			throw new DuplicateRecordException("login already exist");
		}

		/*
		 * //role name column RoleModel rmodel = new RoleModel(); RoleBean rbean =
		 * rmodel.findByPk(bean.getId());
		 */
		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			// rolename column
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnsuccessfullLogin());
			pstmt.setString(10, bean.getGender());
			// pstmt.setDate(11, new java.sql.Date(bean.getLastLogin().getTime()));
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getUserLock());
			pstmt.setString(13, bean.getRegisteredIp());
			pstmt.setString(14, bean.getLastLoginIp());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setString(19, bean.getPhoto());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("User Record Inserted Successfully");

			log.info("User record inserted successfully. ID: " + pk);

		} catch (Exception e) {
			log.error("Exception in adding user record", e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates an existing user record after checking for duplicate login
	 * credentials.
	 *
	 * @param bean the UserBean containing updated user details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another user already uses the same login
	 */
	@Override
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		log.info("Updating user record. ID: " + bean.getId());

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.error("User login already exists: " + bean.getLogin());
			throw new DuplicateRecordException("login already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("update " + getTable() + " set first_name=?, last_name=?, login=?, password=?, "
							+ "dob=?, mobile_no=?, role_id=?, unsuccessfull_login=?, "
							+ "gender=?, last_login=?, user_lock=?, registered_ip=?, "
							+ "last_login_ip=?, created_by=?, modified_by=?, "
							+ "created_datetime=?, modified_datetime=? where id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setInt(8, bean.getUnsuccessfullLogin());
			pstmt.setString(9, bean.getGender());
			// pstmt.setDate(10, new java.sql.Date(bean.getLastLogin().getTime()));
			pstmt.setTimestamp(10, bean.getLastLogin());
			pstmt.setString(11, bean.getUserLock());
			pstmt.setString(12, bean.getRegisteredIp());
			pstmt.setString(13, bean.getLastLoginIp());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("User Record Updated Successfully");

			log.info("User record updated successfully. ID: " + bean.getId());

		} catch (Exception e) {

			log.error("Exception in updating user record. ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the SQL WHERE clause using the non-empty fields of the supplied
	 * UserBean.
	 *
	 * @param bean the UserBean containing search criteria
	 * @return the generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(UserBean bean) {

		log.debug("Building WHERE clause for user search");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}

			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}

			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}

			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" and dob = '" + bean.getDob() + "'");
			}

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");
			}

			if (bean.getRoleId() > 0) {
				sql.append(" and role_id = " + bean.getRoleId());
			}

			if (bean.getUnsuccessfullLogin() > 0) {
				sql.append(" and unsuccessfull_login = " + bean.getUnsuccessfullLogin());
			}

			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}

			if (bean.getLastLogin() != null) {
				sql.append(" and last_login = '" + bean.getLastLogin() + "'");
			}

			if (bean.getUserLock() != null && bean.getUserLock().length() > 0) {
				sql.append(" and user_lock like '" + bean.getUserLock() + "%'");
			}

			if (bean.getRegisteredIp() != null && bean.getRegisteredIp().length() > 0) {
				sql.append(" and registered_ip like '" + bean.getRegisteredIp() + "%'");
			}

			if (bean.getLastLoginIp() != null && bean.getLastLoginIp().length() > 0) {
				sql.append(" and last_login_ip like '" + bean.getLastLoginIp() + "%'");
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
	 * Finds a user record using the login.
	 *
	 * @param login the login of the user to find
	 * @return the matching UserBean, or null if no record is found
	 */
	// find by
	public UserBean findByLogin(String login) {

		log.info("Finding user by login: " + login);

		UserBean bean = findByUniqueColumn("login", login);

		return bean;
	}

	/**
	 * Authenticates a user using the supplied login and password.
	 *
	 * @param login    the user's login
	 * @param password the user's password
	 * @return the authenticated UserBean, or null if authentication fails
	 */
	// Authenticate method
	public UserBean authenticate(String login, String password) {

		log.info("Authenticating user: " + login);

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(password)) {
			log.info("User authentication successful: " + login);
			return bean;
		}

		log.error("User authentication failed: " + login);
		return null;
	}

	/**
	 * Updates the profile photo of a user.
	 *
	 * @param id    the ID of the user
	 * @param photo the photo path
	 * @throws ApplicationException if an application error occurs
	 */
	public void updatePhoto(long id, String photo) throws ApplicationException {
		Connection conn = null;

		log.info("Updating user photo. ID: " + id);

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_USER SET PHOTO = ? WHERE ID = ?");
			pstmt.setString(1, photo);
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

			log.info("User photo updated successfully. ID: " + id);

		} catch (Exception e) {

			log.error("Exception in updating user photo. ID: " + id, e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Changes the password of a user and sends a password-change email.
	 *
	 * @param newPassword the new password
	 * @param oldPassword the current password
	 * @param login       the login of the user
	 * @return the updated UserBean, or null if the old password is incorrect
	 */
	public UserBean changePassword(String newPassword, String oldPassword, String login) {

		log.info("Changing password for user: " + login);

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(oldPassword)) {
			bean.setPassword(newPassword);
			update(bean);

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Password changed successfully for user: " + login);

			return bean;
		}

		log.error("Password change failed for user: " + login);
		return null;

	}

	/**
	 * Sends a password-recovery email to the user.
	 *
	 * @param login the login of the user
	 * @return the UserBean if the user exists, otherwise null
	 */
	public UserBean forgotPassword(String login) {

		log.info("Processing forgot password request for user: " + login);

		UserBean bean = findByLogin(login);

		if (bean != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Forgot password email sent to user: " + login);

			return bean;
		}

		log.error("User not found for forgot password request: " + login);
		return null;

	}

	/**
	 * Registers a new user and sends a registration confirmation email.
	 *
	 * @param bean the UserBean containing registration details
	 * @return the primary key of the registered user
	 */
	public long register(UserBean bean) {

		log.info("Registering new user: " + bean.getLogin());

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		msg.setTo(map.get("login"));
		msg.setSubject("User Rgistration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully");

		log.info("User registration email sent successfully: " + bean.getLogin());

		return pk;
	}

	/**
	 * Returns the database table name used for user records.
	 *
	 * @return the user table name
	 */
	@Override
	public String getTable() {

		return "st_user";
	}

	/**
	 * Creates and returns a new UserBean instance.
	 *
	 * @return a new UserBean object
	 */
	@Override
	public UserBean getBean() {

		return new UserBean();
	}
}
