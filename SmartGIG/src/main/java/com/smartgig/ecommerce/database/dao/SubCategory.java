/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartgig.ecommerce.database.dao;
/**
 *
 * @author tmaeeh
 */
public class SubCategory {
    private int subCatId;/**PK (The created category)*/
    private int CatId;/**FK (The category where the subcategory belongs to)*/
    private String subcategoryName;
    private int sourceId;

    public SubCategory() {
    }

    public SubCategory(int subCatId, int CatId, String subcategoryName, int sourceId) {
        this.subCatId = subCatId;
        this.CatId = CatId;
        this.subcategoryName = subcategoryName;
        this.sourceId = sourceId;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public int getCatId() {
        return CatId;
    }

    public void setCatId(int CatId) {
        this.CatId = CatId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

   
}
