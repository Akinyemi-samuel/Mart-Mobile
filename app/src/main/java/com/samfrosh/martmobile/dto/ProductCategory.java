package com.samfrosh.martmobile.dto;

public class ProductCategory {

    private String id;

    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
