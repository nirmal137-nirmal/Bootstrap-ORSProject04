package in.co.rays.proj4.bean;

import java.sql.Timestamp;
import java.sql.ResultSet;

/**
 * BaseBean is an abstract parent class that provides common properties
 * and methods required by all Bean classes in the application.
 * 
 * It contains common attributes such as ID, createdBy, modifiedBy,
 * createdDatetime, and modifiedDatetime.
 * 
 * It also provides methods to retrieve the primary key and populate
 * bean properties from a ResultSet.
 * 
 * @author Nirmal Fayake
 * @version 1.0
 */
public abstract class BaseBean implements DropDownListBean {

    /** Primary key of the record. */
    protected long id;

    /** Name of the user who created the record. */
    protected String createdBy;

    /** Name of the user who last modified the record. */
    protected String modifiedBy;

    /** Date and time when the record was created. */
    protected Timestamp createdDatetime;

    /** Date and time when the record was last modified. */
    protected Timestamp modifiedDatetime;

    /**
     * Returns the ID of the record.
     * 
     * @return ID of the record
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the record.
     * 
     * @param id ID to be assigned
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the name of the user who created the record.
     * 
     * @return createdBy username
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the user who created the record.
     * 
     * @param createdBy username of the creator
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the name of the user who last modified the record.
     * 
     * @return modifiedBy username
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the name of the user who last modified the record.
     * 
     * @param modifiedBy username of the modifier
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Returns the creation date and time of the record.
     * 
     * @return creation timestamp
     */
    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * Sets the creation date and time of the record.
     * 
     * @param createdDatetime creation timestamp
     */
    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * Returns the last modification date and time of the record.
     * 
     * @return modification timestamp
     */
    public Timestamp getModifiedDatetime() {
        return modifiedDatetime;
    }

    /**
     * Sets the last modification date and time of the record.
     * 
     * @param modifiedDatetime modification timestamp
     */
    public void setModifiedDatetime(Timestamp modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    /**
     * Returns the ID of the record as a String.
     * This method is implemented from DropDownListBean.
     * 
     * @return ID as String
     */
    @Override
    public String getKey() {
        return id + "";
    }

    /**
     * Populates the bean properties using the values retrieved
     * from the given ResultSet.
     * 
     * @param rs ResultSet containing database record information
     */
    public void setResultSet(ResultSet rs) {
        try {
            setId(rs.getLong("id"));
            setCreatedBy(rs.getString("created_by"));
            setModifiedBy(rs.getString("modified_by"));
            setCreatedDatetime(rs.getTimestamp("created_datetime"));
            setModifiedDatetime(rs.getTimestamp("modified_datetime"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}