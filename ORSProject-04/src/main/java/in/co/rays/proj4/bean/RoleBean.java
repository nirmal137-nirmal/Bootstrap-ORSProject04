package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * RoleBean is a Java Bean class that stores role-related information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * 
 * @version 1.0
 */
public class RoleBean extends BaseBean {
	
	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int FACULTY = 3;
	public static final int COLLEGE = 4;
	public static final int KIOSK = 5;

	/** Name of the role. */
	private String name;

	/** Description of the role. */
	private String description;

	/**
	 * Returns the name of the role.
	 *
	 * @return role name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 *
	 * @param name role name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the role.
	 *
	 * @return role description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the role.
	 *
	 * @param description role description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Populates the RoleBean properties using the values
	 * retrieved from the given ResultSet.
	 *
	 * @param rs ResultSet containing role record information
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

	/**
	 * Returns the name of the role as the display value.
	 *
	 * @return role name
	 */
	@Override
	public String getValue() {
		return name;
	}

}