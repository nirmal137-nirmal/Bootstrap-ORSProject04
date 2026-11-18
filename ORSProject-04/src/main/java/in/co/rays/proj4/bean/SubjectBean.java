package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * SubjectBean is a Java Bean class that stores subject-related information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class SubjectBean extends BaseBean {

	/** Name of the subject. */
	private String name;

	/** Description of the subject. */
	private String description;

	/** ID of the course associated with the subject. */
	private long courseId;

	/**
	 * Returns the name of the subject.
	 *
	 * @return subject name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the subject.
	 *
	 * @param name subject name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the subject.
	 *
	 * @return subject description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the subject.
	 *
	 * @param description subject description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the course ID associated with the subject.
	 *
	 * @return course ID
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with the subject.
	 *
	 * @param courseId ID of the course
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Returns the display value of the subject.
	 *
	 * @return subject value
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Populates the SubjectBean properties using the values
	 * retrieved from the given ResultSet.
	 *
	 * @param rs ResultSet containing subject record information
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		try {
			setId(rs.getLong(1));
			setName(rs.getString(2));
			setDescription(rs.getString(3));
			setCourseId(rs.getLong(4));
			setCreatedBy(rs.getString(5));
			setModifiedBy(rs.getString(6));
			setCreatedDatetime(rs.getTimestamp(7));
			setModifiedDatetime(rs.getTimestamp(8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}