package com.chornyiua.myfinance.dto;

/**
 * Created by ChornyiUA on 12.04.2016.
 */
public class BillDTO {
    private int ID;
    private long date;
    private int categoryID;
    private int change;
    private int value;
    private String comment;

    public BillDTO(long date, int categoryID, int change, int value, String comment) {
        this.date = date;
        this.categoryID = categoryID;
        this.change = change;
        this.value = value;
        this.comment = comment;
    }

    public BillDTO(int ID, long date, int categoryID, int change, int value, String comment) {

        this.ID = ID;
        this.date = date;
        this.categoryID = categoryID;
        this.change = change;
        this.value = value;
        this.comment = comment;
    }

    public BillDTO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
