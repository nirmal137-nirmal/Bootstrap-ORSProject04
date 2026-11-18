package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 * UserBean is a Java Bean class that stores user-related information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class UserBean extends BaseBean {

	/** First name of the user. */
	private String firstName;

	/** Last name of the user. */
	private String lastName;

	/** Login ID of the user. */
	private String login;

	/** Password of the user. */
	private String password;

	/** Confirmation password of the user. */
	private String confirmPassword;

	/** Date of birth of the user. */
	private Date dob;

	/** Mobile number of the user. */
	private String mobileNo;

	/** ID of the role assigned to the user. */
	private long roleId;

	/** Number of unsuccessful login attempts. */
	private int unsuccessfullLogin;

	/** Gender of the user. */
	private String gender;

	/** Last login date and time of the user. */
	private Timestamp lastLogin;

	/** Lock status of the user account. */
	private String userLock;

	/** IP address from which the user registered. */
	private String registeredIp;

	/** IP address of the user's last login. */
	private String lastLoginIp;

	/** Photo of the user. */
	private String photo;

	/**
	 * Returns the first name of the user.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the user.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName last name of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the login ID of the user.
	 *
	 * @return login ID
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login ID of the user.
	 *
	 * @param login login ID
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns the password of the user.
	 *
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the confirmation password of the user.
	 *
	 * @return confirmation password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirmation password of the user.
	 *
	 * @param confirmPassword confirmation password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Returns the date of birth of the user.
	 *
	 * @return date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 *
	 * @param dob date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Returns the mobile number of the user.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the user.
	 *
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Returns the role ID assigned to the user.
	 *
	 * @return role ID
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role ID assigned to the user.
	 *
	 * @param roleId role ID
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Returns the number of unsuccessful login attempts.
	 *
	 * @return unsuccessful login count
	 */
	public int getUnsuccessfullLogin() {
		return unsuccessfullLogin;
	}

	/**
	 * Sets the number of unsuccessful login attempts.
	 *
	 * @param unsuccessfullLogin unsuccessful login count
	 */
	public void setUnsuccessfullLogin(int unsuccessfullLogin) {
		this.unsuccessfullLogin = unsuccessfullLogin;
	}

	/**
	 * Returns the gender of the user.
	 *
	 * @return user gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 *
	 * @param gender user gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the last login date and time.
	 *
	 * @return last login timestamp
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the last login date and time.
	 *
	 * @param lastLogin last login timestamp
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Returns the lock status of the user account.
	 *
	 * @return user lock status
	 */
	public String getUserLock() {
		return userLock;
	}

	/**
	 * Sets the lock status of the user account.
	 *
	 * @param userLock user lock status
	 */
	public void setUserLock(String userLock) {
		this.userLock = userLock;
	}

	/**
	 * Returns the registered IP address of the user.
	 *
	 * @return registered IP address
	 */
	public String getRegisteredIp() {
		return registeredIp;
	}

	/**
	 * Sets the registered IP address of the user.
	 *
	 * @param registeredIp registered IP address
	 */
	public void setRegisteredIp(String registeredIp) {
		this.registeredIp = registeredIp;
	}

	/**
	 * Returns the IP address of the user's last login.
	 *
	 * @return last login IP address
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * Sets the IP address of the user's last login.
	 *
	 * @param lastLoginIp last login IP address
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * Returns the photo of the user.
	 *
	 * @return user photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo of the user.
	 *
	 * @param photo user photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Returns the gender as the display value.
	 *
	 * @return user gender
	 */
	@Override
	public String getValue() {

		return gender;
	}

	/**
	 * Populates the UserBean properties using the values
	 * retrieved from the given ResultSet.
	 *
	 * @param rs ResultSet containing user record information
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		try {
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setLogin(rs.getString("login"));
			setPassword(rs.getString("password"));
			setDob(rs.getDate("dob"));
			setMobileNo(rs.getString("mobile_no"));
			setRoleId(rs.getLong("role_id"));
			setUnsuccessfullLogin(rs.getInt("unsuccessfull_login"));
			setGender(rs.getString("gender"));
			setLastLogin(rs.getTimestamp("last_login"));
			setUserLock(rs.getString("user_lock"));
			setRegisteredIp(rs.getString("registered_ip"));
			setLastLoginIp(rs.getString("last_login_ip"));
			setPhoto(rs.getString("photo"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.setResultSet(rs);
	}

}