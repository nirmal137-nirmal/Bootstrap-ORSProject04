package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

/**
 * StudentBean is a Java Bean class that stores student-related information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class StudentBean extends BaseBean {

	/** ID of the college associated with the student. */
	private long collegeId;

	/** Name of the college associated with the student. */
	private String collegeName;

	/** First name of the student. */
	private String firstName;

	/** Last name of the student. */
	private String lastName;

	/** Date of birth of the student. */
	private Date dateOfBirth;

	/** Mobile number of the student. */
	private String mobileNo;

	/** Email address of the student. */
	private String email;


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
	 * Returns the first name of the student.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 *
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the student.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 *
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the date of birth of the student.
	 *
	 * @return date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the student.
	 *
	 * @param dateOfBirth student date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Returns the mobile number of the student.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the student.
	 *
	 * @param mobileNo student mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Returns the email address of the student.
	 *
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the student.
	 *
	 * @param email student email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the college name as the display value.
	 *
	 * @return college name
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return collegeName;
	}

	/**
	 * Populates the StudentBean properties using the values
	 * retrieved from the given ResultSet.
	 *
	 * @param rs ResultSet containing student record information
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		try {

			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setDateOfBirth(rs.getDate("date_of_birth"));
			setMobileNo(rs.getString("mobile_no"));
			setEmail(rs.getString("email"));;

		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}