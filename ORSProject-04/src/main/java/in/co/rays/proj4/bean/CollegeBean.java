package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * CollegeBean is a Java Bean class that stores college-related information.
 * 
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 * 
 * It contains college details such as name, address, state, city, and phone number.
 * 
 * @author Nirmal Fayake
 * @version 1.0
 */
public class CollegeBean extends BaseBean {

    /** Name of the college. */
    private String name;

    /** Address of the college. */
    private String address;

    /** State in which the college is located. */
    private String State;

    /** City in which the college is located. */
    private String city;

    /** Contact phone number of the college. */
    private String phoneNo;

    /**
     * Returns the name of the college as its display value.
     * 
     * @return name of the college
     */
    @Override
    public String getValue() {
        return name;
    }

    /**
     * Returns the name of the college.
     * 
     * @return college name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the college.
     * 
     * @param name name of the college
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the college.
     * 
     * @return college address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the college.
     * 
     * @param address address of the college
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the state where the college is located.
     * 
     * @return state name
     */
    public String getState() {
        return State;
    }

    /**
     * Sets the state where the college is located.
     * 
     * @param state state name
     */
    public void setState(String state) {
        State = state;
    }

    /**
     * Returns the city where the college is located.
     * 
     * @return city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the college is located.
     * 
     * @param city city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the contact phone number of the college.
     * 
     * @return college phone number
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the contact phone number of the college.
     * 
     * @param phoneNo college phone number
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Populates the CollegeBean properties using the values
     * retrieved from the given ResultSet.
     * 
     * It also calls the parent class method to populate
     * common BaseBean properties.
     * 
     * @param rs ResultSet containing college record information
     */
    @Override
    public void setResultSet(ResultSet rs) {
        try {
            setName(rs.getString("name"));
            setAddress(rs.getString("address"));
            setState(rs.getString("state"));
            setCity(rs.getString("city"));
            setPhoneNo(rs.getString("phone_no"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.setResultSet(rs);
    }

}