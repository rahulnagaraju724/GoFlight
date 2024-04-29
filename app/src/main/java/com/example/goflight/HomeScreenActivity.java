package com.example.goflight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen); // Set the content view to the home screen layout

        // Find the "Search Flights" button by its ID

        ImageButton searchFlightsButton = findViewById(R.id.view_search_flights);
        // Set an OnClickListener to the "Search Flights" button
        searchFlightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the button is clicked, start the MainActivity
                System.out.println("I am inside onclick for search Flights button");
                startActivity(new Intent(HomeScreenActivity.this, MainActivity.class));
            }
        });

        ImageButton userBookingsButton = findViewById(R.id.view_bookings_button);
        userBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to user bookings page
                startActivity(new Intent(HomeScreenActivity.this, UserBookingsActivity.class));
            }
        });

        ImageButton specialOffersButton = findViewById(R.id.special_offers_button);
        specialOffersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to user bookings page
                startActivity(new Intent(HomeScreenActivity.this, FlightOffersActivity.class));
            }
        });
    }

    // Other methods of MainActivity...
}
