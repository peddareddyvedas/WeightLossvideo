package com.vedas.weightloss.Controllers;

import android.content.Context;
import android.view.View;

import com.vedas.weightloss.FoodModule.DayOneActivity;
import com.vedas.weightloss.Models.DayFoodObject;

import java.util.ArrayList;

/**
 * Created by VEDAS on 6/13/2018.
 */

public class DayFoodController {
    public static DayFoodController myObj;
    Context context;
    public static DayFoodObject selectedFoodObject;

    public static DayFoodController getInstance() {
        if (myObj == null) {
            myObj = new DayFoodController();
            selectedFoodObject = new DayFoodObject();
            selectedFoodObject.breakfastArrayList = new ArrayList<>();
            selectedFoodObject.weightArrayList = new ArrayList<>();
            selectedFoodObject.exerciseArrayList = new ArrayList<>();
            selectedFoodObject.waterArrayList = new ArrayList<>();
            selectedFoodObject.lunchArrayList = new ArrayList<>();
            selectedFoodObject.dinnerArrayList = new ArrayList<>();
            selectedFoodObject.snacksArrayList = new ArrayList<>();
        }
        return myObj;
    }

    public void fillContext(Context context1) {
        context = context1;
    }

    public void loadTextviewdata(DayOneActivity.DashBoardAdapter.MainVH holder, int section, int relativePosition, int absolutePosition) {
        if (section == 1) {
            if (DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.get(relativePosition).getFoodName(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.breakfastArrayList.get(relativePosition).getEsimatedCalories(), section, relativePosition, absolutePosition));

            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }
        if (section == 0) {
            if (DayFoodController.getInstance().selectedFoodObject.weightArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.weightArrayList.get(relativePosition).getWeight(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.weightArrayList.get(relativePosition).getUnits(), section, relativePosition, absolutePosition));

            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }

        if (section == 2) {
            if (DayFoodController.getInstance().selectedFoodObject.lunchArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.lunchArrayList.get(relativePosition).getFoodName(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.lunchArrayList.get(relativePosition).getEsimatedCalories(), section, relativePosition, absolutePosition));

            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }
        if (section == 3) {
            if (DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.get(relativePosition).getFoodName(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.dinnerArrayList.get(relativePosition).getEsimatedCalories(), section, relativePosition, absolutePosition));

            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }
        if (section == 4) {
            if (DayFoodController.getInstance().selectedFoodObject.snacksArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.snacksArrayList.get(relativePosition).getFoodName(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.snacksArrayList.get(relativePosition).getEsimatedCalories(), section, relativePosition, absolutePosition));

            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }
        //exercise
        if (section == 5) {
            if (DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.get(relativePosition).getExerciseName(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.exerciseArrayList.get(relativePosition).getEstimatedCaloriesBurned(), section, relativePosition, absolutePosition));
            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }
        //water
        if (section == 6) {
            if (DayFoodController.getInstance().selectedFoodObject.waterArrayList.size() > 0) {
                holder.view.setVisibility(View.VISIBLE);
                holder.name.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.waterArrayList.get(relativePosition).getWaterContent(), section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format(DayFoodController.getInstance().selectedFoodObject.waterArrayList.get(relativePosition).getWaterUnits(), section, relativePosition, absolutePosition));
            } else {
                holder.view.setVisibility(View.GONE);
                holder.name.setText(
                        String.format("", section, relativePosition, absolutePosition));
                holder.calories.setText(
                        String.format("", section, relativePosition, absolutePosition));

            }
        }
    }
}
