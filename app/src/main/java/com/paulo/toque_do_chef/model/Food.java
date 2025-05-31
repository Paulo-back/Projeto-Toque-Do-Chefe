package com.paulo.toque_do_chef.model;

import java.io.Serializable;

public class Food implements Serializable {
    int imgFood;
    String foodName;
    String foodDescription;

    public Food(int imgFood, String foodName, String foodDescription) {
        this.imgFood = imgFood;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
    }

    public int getImgFood() {
        return imgFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

}
