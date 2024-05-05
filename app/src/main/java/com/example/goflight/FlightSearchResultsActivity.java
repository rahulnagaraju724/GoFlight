package com.example.goflight;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightSearchResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlightAdapter flightAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_layout2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set OnClickListener to the toolbar
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home page
                Intent intent = new Intent(FlightSearchResultsActivity.this, HomeScreenActivity.class);
                startActivity(intent);
//                finish(); // Optional: finish the current activity to prevent going back to it when pressing back
            }
        });

        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.flight_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightAdapter = new FlightAdapter();
        recyclerView.setAdapter(flightAdapter);

        // Retrieve search parameters from intent extras
        String source = getIntent().getStringExtra("source");
        String destination = getIntent().getStringExtra("destination");
        String departureDate = getIntent().getStringExtra("departureDate");

        // Perform flight search using the search parameters
        List<Flight> flights = dbHelper.searchFlights2(source, destination, departureDate);

        for(Flight flight:flights){

//            flight.calculateDuration();

            System.out.println(flight.toString());

        }

        // Update RecyclerView with search results
        flightAdapter.setFlights(flights);
        flightAdapter.notifyDataSetChanged();
    }
}
