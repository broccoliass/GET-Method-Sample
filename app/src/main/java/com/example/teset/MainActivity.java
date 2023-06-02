package com.example.teset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<JSONObject> fitnessDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fitnessDataList = new ArrayList<>();

        viewRecentSession();
    }

    private void viewRecentSession() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://128.199.74.70/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject fitnessObj = response.getJSONObject(i);
                                fitnessDataList.add(fitnessObj);
                            }

                            // Process the fitness data or display it as desired
                            // For example, you can iterate through the list and extract values

                            LinearLayout container = findViewById(R.id.container);
                            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

                            for (JSONObject fitnessData : fitnessDataList) {
                                int id = fitnessData.getInt("id");
                                String date = fitnessData.getString("date");
                                int steps = fitnessData.getInt("steps");
                                int calories = fitnessData.getInt("calories");


                                // Inflate the layout for each data entry
                                View entryView = inflater.inflate(R.layout.item_fitness_data, null);

                                // Set the values to the respective TextView elements
                                TextView textViewId = entryView.findViewById(R.id.textViewId);
                                textViewId.setText("ID: " + id);

                                TextView textViewDate = entryView.findViewById(R.id.textViewDate);
                                textViewDate.setText("Date: " + date);

                                TextView textViewSteps = entryView.findViewById(R.id.textViewSteps);
                                textViewSteps.setText("Steps: " + steps);

                                TextView textViewCalories = entryView.findViewById(R.id.textViewCalories);
                                textViewCalories.setText("Calories: " + calories);

                                // Add the entryView to the container
                                container.addView(entryView);

                                // Do something with the extracted values
                                Log.d("FitnessData", "ID: " + id + ", Date: " + date + ", Steps: " + steps + ", Calories: " + calories);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", "Error: " + error.getMessage());
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}
