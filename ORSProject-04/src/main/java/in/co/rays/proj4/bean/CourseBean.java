package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * CourseBean is a Java Bean class that stores course-related information.
 * It extends BaseBean to inherit common properties of the application.
 *
 * @author Nirmal Fayake
 * @version 1.0
 */
public class CourseBean extends BaseBean {

    /** Name of the course. */
    private String name;

    /** Description of the course. */
    private String description;

    /** Duration of the course. */
    private String duration;

    /**
     * Returns the name of the course.
     *
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the course.
     *
     * @return course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description course description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the duration of the course.
     *
     * @return course duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the course.
     *
     * @param duration course duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Returns the display value of the course.
     *
     * @return course value
     */
    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Populates the CourseBean properties using the values
     * retrieved from the given ResultSet.
     *
     * @param rs ResultSet containing course record information
     */
    @Override
    public void setResultSet(ResultSet rs) {
        try {
            setId(rs.getLong(1));
            setName(rs.getString(2));
            setDescription(rs.getString(3));
            setDuration(rs.getString(4));
            setCreatedBy(rs.getString(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setResultSet(rs);
    }

}