package com.shohda.bazideraz.model;



import java.util.ArrayList;

public class SubCategory {
    private String id, name, image, categoryId,status,maxLevel,No_ofque;


    public SubCategory() {
    }

    public String getNo_ofque() {
        return No_ofque;
    }

    public void setNo_ofque(String no_ofque) {
        No_ofque = no_ofque;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getMaxLevel() {
        return maxLevel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setMaxLevel(String maxLevel) {
        this.maxLevel = maxLevel;
    }


}
