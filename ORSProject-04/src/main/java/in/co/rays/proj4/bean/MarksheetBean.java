package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * MarksheetBean is a Java Bean class that stores student marksheet information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class MarksheetBean extends BaseBean {

	/** Roll number of the student. */
	private String rollNo;

	/** ID of the student. */
	private long studentId;

	/** Name of the student. */
	private String name;

	/** Marks obtained in Physics. */
	private int physics;

	/** Marks obtained in Chemistry. */
	private int chemistry;

	/** Marks obtained in Mathematics. */
	private int maths;

	/**
	 * Returns the roll number of the student.
	 *
	 * @return student roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets the roll number of the student.
	 *
	 * @param rollNo student roll number
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Returns the student ID.
	 *
	 * @return student ID
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student ID.
	 *
	 * @param studentId student ID
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Returns the name of the student.
	 *
	 * @return student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the student.
	 *
	 * @param name student name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the marks obtained in Physics.
	 *
	 * @return Physics marks
	 */
	public int getPhysics() {
		return physics;
	}

	/**
	 * Sets the marks obtained in Physics.
	 *
	 * @param physics Physics marks
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * Returns the marks obtained in Chemistry.
	 *
	 * @return Chemistry marks
	 */
	public int getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the marks obtained in Chemistry.
	 *
	 * @param chemistry Chemistry marks
	 */
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Returns the marks obtained in Mathematics.
	 *
	 * @return Mathematics marks
	 */
	public int getMaths() {
		return maths;
	}

	/**
	 * Sets the marks obtained in Mathematics.
	 *
	 * @param maths Mathematics marks
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}

	/**
	 * Returns the display value of the marksheet.
	 *
	 * @return marksheet value
	 */
	@Override
	public String getValue() {
		return null;
	}

	/**
	 * Populates the MarksheetBean properties using the values
	 * retrieved from the given ResultSet.
	 *
	 * @param rs ResultSet containing marksheet record information
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setRollNo(rs.getString("roll_no"));
			setStudentId(rs.getLong("student_id"));
			setName(rs.getString("name"));
			setPhysics(rs.getInt("physics"));
			setChemistry(rs.getInt("chemistry"));
			setMaths(rs.getInt("maths"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}