package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.Field;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * The Run activity displays and manages fitness data from Google Fit.
 * It is responsible for fetching and displaying the step count, distance, calories burned,
 * and heart rate, as well as handling bottom navigation and popup menu interactions.
 */
public class Run extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Context context = Run.this;
    private GoogleFitManager googleFitManager;

    // UI elements
    private TextView stepCountTextView;
    private TextView distanceTextView;
    private TextView caloriesTextView;
    private TextView heartRateTextView;
    private Button refreshDataButton;

    /**
     * Initializes the activity, sets up the Google Fit manager, UI elements, and event listeners.
     *
     * @param savedInstanceState the saved instance state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        // Initialize Google Fit manager and request necessary permissions
        googleFitManager = new GoogleFitManager(this, context);
        googleFitManager.requestGoogleFitPermissions();

        // Initialize UI elements
        stepCountTextView = findViewById(R.id.step_count_text_view);
        distanceTextView = findViewById(R.id.distance_text_view);
        caloriesTextView = findViewById(R.id.calories_text_view);
        heartRateTextView = findViewById(R.id.heart_rate_text_view);
        refreshDataButton = findViewById(R.id.refresh_data_button);

        // Add a click listener for the refresh data button
        refreshDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataAndUpdateUI();
            }
        });

        // Set up bottom navigation and its event listeners
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.run);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calorie:
                        Toast.makeText(context, "Calorie", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));
                        return true;

                    case R.id.run:
                        Toast.makeText(context, "Run", Toast.LENGTH_SHORT).show();
                        fetchDataAndUpdateUI();
                        return true;

                    case R.id.social:
                        Toast.makeText(context, "Social", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Social.class));
                        return true;

                    case R.id.trends:
                        Toast.makeText(Run.this, "Trends", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Trend.class));
                        return true;
                }
                return false;
            }
        });

        // Set up the floating action button and its event listener
        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(context, "Selected:" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (item.getItemId() == R.id.newMeal) {
                            startActivity(new Intent(context, NewMeal.class));
                        } else if (item.getItemId() == R.id.newRun) {
                            startActivity(new Intent(context, MapsActivity.class));
                        }
                        else if (item.getItemId()==R.id.newPost){
                            startActivity(new Intent(context, NewPost.class));
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    /**
     * Fetches fitness data from Google Fit manager and updates the UI elements.
     */
    private void fetchDataAndUpdateUI() {
        googleFitManager.getTodayStepCount(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                int stepCount = dataPoint.getValue(Field.FIELD_STEPS).asInt();
                stepCountTextView.setText("Step Count: " + stepCount);
            }
        });
        googleFitManager.getTodayDistance(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                float distance = dataPoint.getValue(Field.FIELD_DISTANCE).asFloat();
                distanceTextView.setText("Distance: " + distance + " meters");
            }
        });
        googleFitManager.getTodayCaloriesBurned(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                float calories = dataPoint.getValue(Field.FIELD_CALORIES).asFloat();
                caloriesTextView.setText("Calories Burned: " + calories + " kcal");
            }
        });

        googleFitManager.getLatestHeartRate(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                float heartRate = dataPoint.getValue(Field.FIELD_BPM).asFloat();
                heartRateTextView.setText("Heart Rate: " + heartRate + " bpm");
            }
        });
    }
}
/**
 *
 The updated code now includes comments explaining the purpose of the `Run` activity, the methods, and the different sections of code that
 are responsible for initializing the UI, setting up event listeners, and fetching and updating fitness data from the Google Fit manager.

 */

