package com.example.healthmate;

public class UserData {
    private static UserData instance = null;
    private String userName;
    private int sex;
    private int weight;
    private int height;
    private int calorie_intake_goal;
    private int workoutGoal;
    private boolean isDefault=true;

    private UserData() {

    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
            instance.populateDefault();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public int getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }


    public int getHeight() {
        return height;
    }

    public int getCalorieIntakeGoal() {
        return calorie_intake_goal;
    }

    public int getWorkoutGoal() {
        return workoutGoal;
    }
    public boolean isDefault(){
        return this.isDefault;
    }

    public void UpdateData(String userName, int sex, int weight, int height, int calorie_intake_goal, int workoutGoal) {
        this.userName=userName;
        this.sex=sex;
        this.weight=weight;
        this.height=height;
        this.calorie_intake_goal = calorie_intake_goal;
        this.workoutGoal = workoutGoal;
        this.isDefault=false;
    }
    public void populateDefault() {
        this.userName="Guru";
        this.sex=1;
        this.weight=89;
        this.height=181;
        this.calorie_intake_goal = 2400;
        this.workoutGoal = 2900;
    }



}