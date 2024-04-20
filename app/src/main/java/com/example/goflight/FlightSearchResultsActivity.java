package com.example.goflight;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
        List<Flight> flights = dbHelper.searchFlights(source, destination, departureDate);

        for(Flight flight:flights){

            flight.calculateDuration();

            System.out.println(flight.toString());

        }

        // Update RecyclerView with search results
        flightAdapter.setFlights(flights);
        flightAdapter.notifyDataSetChanged();
    }
}
