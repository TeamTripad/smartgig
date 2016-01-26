package com.smartgig.ecommerce.database.dao;

/**
 * Category sa product subCategroy is possible
 * CATEGORY SHOULD BE UNIQUE
 */
public class Category {

    private int catId;
    private String categoryName;

    public Category() {
    }

    public Category(int catId, String categoryName) {
        this.catId = catId;
        this.categoryName = categoryName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
