package com.kakaopay.restapi.model;

public class Category {
    private Integer categoryId;
    private String categoryName;

    public Category(Integer categoryId, String categoryName) {
        super();
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
