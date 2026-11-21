package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

/**
 * FacultyBean is a Java Bean class that stores faculty-related information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class FacultyBean extends BaseBean {

	/** ID of the college associated with the faculty. */
	private long collegeId;

	/** Name of the college associated with the faculty. */
	private String collegeName;

	/** First name of the faculty member. */
	private String firstName;

	/** Last name of the faculty member. */
	private String lastName;

	/** Email address of the faculty member. */
	private String email;

	/** Mobile number of the faculty member. */
	private String mobileNo;

	/** Residential address of the faculty member. */
	private String address;

	/** Gender of the faculty member. */
	private String gender;

	/** Date of birth of the faculty member. */
	private Date dateOfBirth;

	/**
	 * Returns the college ID.
	 *
	 * @return college ID
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college ID.
	 *
	 * @param collegeId ID of the college
	 */
	
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Returns the college name.
	 *
	 * @return college name
	 */
	
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the college name.
	 *
	 * @param collegeName name of the college
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Returns the first name of the faculty member.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the faculty member.
	 *
	 * @param firstName first name
	 */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the faculty member.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the faculty member.
	 *
	 * @param lastName last name
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the email address of the faculty member.
	 *
	 * @return email address
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the faculty member.
	 *
	 * @param email email address
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the mobile number of the faculty member.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the faculty member.
	 *
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Returns the residential address of the faculty member.
	 *
	 * @return faculty address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the residential address of the faculty member.
	 *
	 * @param address faculty address
	 */
	
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the gender of the faculty member.
	 *
	 * @return gender
	 */
	
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the faculty member.
	 *
	 * @param gender faculty gender
	 */
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the date of birth of the faculty member.
	 *
	 * @return date of birth
	 */
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the faculty member.
	 *
	 * @param dateOfBirth faculty date of birth
	 */
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Returns the college name as the display value.
	 *
	 * @return college name
	 */
	@Override
	public String getValue() {
		return collegeName;
	}

	/**
	 * Populates the FacultyBean properties using the values
	 * retrieved from the given ResultSet.
	 *
	 * @param rs ResultSet containing faculty record information
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setId(rs.getLong(1));
			setCollegeId(rs.getLong(2));
			setCollegeName(rs.getString(3));
			setFirstName(rs.getString(4));
			setLastName(rs.getString(5));
			setEmail(rs.getString(6));
			setMobileNo(rs.getString(7));
			setAddress(rs.getString(8));
			setGender(rs.getString(9));
			setDateOfBirth(rs.getTimestamp(10));
			setCreatedBy(rs.getString(11));
			setModifiedBy(rs.getString(12));
			setCreatedDatetime(rs.getTimestamp(13));
			setModifiedDatetime(rs.getTimestamp(14));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}