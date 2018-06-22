package com.vedas.weightloss.Models;

/**
 * Created by VEDAS on 6/13/2018.
 */

public class BreakfastObject {
    String foodName;
    String servingSize;
    String servingPerContainer;
    String esimatedCalories;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    String units;


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getServingPerContainer() {
        return servingPerContainer;
    }

    public void setServingPerContainer(String servingPerContainer) {
        this.servingPerContainer = servingPerContainer;
    }

    public String getEsimatedCalories() {
        return esimatedCalories;
    }

    public void setEsimatedCalories(String esimatedCalories) {
        this.esimatedCalories = esimatedCalories;
    }


}
