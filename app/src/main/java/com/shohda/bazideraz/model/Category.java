package com.shohda.bazideraz.model;


import java.util.ArrayList;

public class Category {
    private String id, name, image, product_price;
    public ArrayList<com.shohda.bazideraz.model.Category> subCategoryList;



    public Category() {
    }

    public Category(String id, String name, String image,String product_price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.product_price = product_price;
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



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return product_price;
    }

    public void setPrice(String product_price) {
        this.product_price = product_price;
    }
}
