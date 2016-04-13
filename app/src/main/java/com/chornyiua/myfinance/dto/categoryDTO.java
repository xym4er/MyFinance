package com.chornyiua.myfinance.dto;

/**
 * Created by ChornyiUA on 12.04.2016.
 */
public class CategoryDTO {
    private int ID;
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(int ID, String categoryName) {
        this.ID = ID;
        this.categoryName = categoryName;
    }

    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
