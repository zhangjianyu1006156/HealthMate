package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewMeal extends AppCompatActivity {

    private ArrayList<plus_AddMeal> central_data;

    private void setupMeal(String mealName, String proteinText, String fatText, String carbsText, String caloriesText, String timeText) {
        newMealHolder.getInstance().storeMeal(new plus_AddMeal(mealName, proteinText,fatText,  carbsText,caloriesText, timeText));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmeal);
        Button cancel = findViewById(R.id.cancel_button);
        central_data = newMealHolder.getInstance().getData();

        Button add = findViewById(R.id.add_meal);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editMealName = findViewById(R.id.editMealName);
                EditText protein = findViewById(R.id.editTextNumber2);
                EditText fat = findViewById(R.id.editTextNumber );
                EditText carbs = findViewById(R.id.editTextNumber6);
                EditText totalcalories = findViewById(R.id.editTextNumber3);
                EditText time = findViewById(R.id.editTextTime);
                String mealName = editMealName.getText().toString();
                String proteinText = protein.getText().toString();
                String fatText = fat.getText().toString();
                String carbsText = carbs.getText().toString();
                String caloriesText = totalcalories.getText().toString();
                String timeText = time.getText().toString();
                if (!mealName.isEmpty() && !proteinText.isEmpty() && !fatText.isEmpty() &&
                        !carbsText.isEmpty() && !caloriesText.isEmpty() && !timeText.isEmpty()) {
                    setupMeal(mealName, proteinText, fatText, carbsText, caloriesText, timeText);

                    Log.d("Great Sucess",newMealHolder.getInstance().TotalCalories()+" ");
                    startActivity(new Intent(NewMeal.this,MainActivity.class).putExtra("Key",newMealHolder.getInstance().TotalCalories()+" "));
                }
                else{
                    Toast.makeText(NewMeal.this,"Great Success",Toast.LENGTH_SHORT).show();
                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewMeal.this,central_data.size()+"Guru ",Toast.LENGTH_SHORT).show();
            }
        });





    }
}