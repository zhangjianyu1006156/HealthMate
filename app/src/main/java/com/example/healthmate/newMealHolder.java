package com.example.healthmate;

import android.database.Observable;

import java.util.ArrayList;
import java.util.List;

public class newMealHolder extends Observable {
    private static newMealHolder instance;
    private List<MyObserver> observers = new ArrayList<>();

    private ArrayList<plus_AddMeal> data;

    private newMealHolder() {
        data = new ArrayList<>();
    }

    public static newMealHolder getInstance() {
        if (instance == null) {
            instance = new newMealHolder();
        }
        return instance;
    }

    public void storeMeal(plus_AddMeal meal) {
        data.add(meal);
        notifyObservers();
    }
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.OnChange();
        }
    }


    public int getMealCount() {
        return data.size();
    }

    public ArrayList<plus_AddMeal> getData() {
        return data;
    }

    public plus_AddMeal getMeal(int position) {
        return data.get(position);
    }
    public int TotalCalories(){
        int totalCalories = 0;
        for (plus_AddMeal meal : data) {
            totalCalories +=Integer.parseInt(meal.getTotalCalories());
        }
        return totalCalories;
    }
    public int TotalProtein() {
        int totalProtein = 0;
        for (plus_AddMeal meal : data) {
            totalProtein += Integer.parseInt(meal.getProtein());
        }
        return totalProtein;
    }

    public int TotalFat() {
        int totalFat = 0;
        for (plus_AddMeal meal : data) {
            totalFat += Integer.parseInt(meal.getFat());
        }
        return totalFat;
    }

    public int TotalCarbs() {
        int totalCarbs = 0;
        for (plus_AddMeal meal : data) {
            totalCarbs += Integer.parseInt(meal.getCarbs());
        }
        return totalCarbs;
    }

}