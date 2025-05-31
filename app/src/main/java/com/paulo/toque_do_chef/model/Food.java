package com.paulo.toque_do_chef.model;

import java.io.Serializable;

public class Food implements Serializable {
    int imgFood;
    String foodName;
    String foodDescription;

    String foodRecipe;

    String foodIngredients;
    float Rating;

    public Food(int imgFood, String foodName, String foodDescription, String foodIngredients,String foodRecipe,float Rating) {
        this.imgFood = imgFood;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodRecipe = foodRecipe;
        this.foodIngredients = foodIngredients;
        this.Rating = Rating;
    }
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

    public String getFoodRecipe() {
        return foodRecipe;
    }

    public void setFoodRecipe(String foodRecipe) {
        this.foodRecipe = foodRecipe;
    }

    public String getFoodPrepare() {
        return foodIngredients;
    }

    public void setFoodPrepare(String foodPrepare) {
        this.foodIngredients = foodPrepare;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}
