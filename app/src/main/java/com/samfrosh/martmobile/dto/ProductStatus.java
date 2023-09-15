package com.samfrosh.martmobile.dto;

public class ProductStatus {

    private String id;

    private String statusName;

    public ProductStatus(String id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}