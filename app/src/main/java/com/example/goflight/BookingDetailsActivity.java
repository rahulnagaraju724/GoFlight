package com.example.goflight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;




public class BookingDetailsActivity extends AppCompatActivity {

    private TextView bookingIdTextView;
    private TextView bookingDateTextView;
    private TextView flightTextView;
    private TextView routeTextView;
    private TextView departureArrivalTextView;
    private TextView passengerTextView;
    private TextView phoneSeatTextView;
    private TextView pricePaymentTextView;
    private Button editButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // Initialize views
        bookingIdTextView = findViewById(R.id.text_booking_id);
        bookingDateTextView = findViewById(R.id.text_booking_date);
        flightTextView = findViewById(R.id.text_flight);
        routeTextView = findViewById(R.id.text_route);
        departureArrivalTextView = findViewById(R.id.text_departure_arrival);
        passengerTextView = findViewById(R.id.text_passenger);
        phoneSeatTextView = findViewById(R.id.text_phone_seat);
        pricePaymentTextView = findViewById(R.id.text_price_payment);
        editButton = findViewById(R.id.edit_button);
        deleteButton = findViewById(R.id.delete_button);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bookingId")) {
//            booking = (Booking) intent.getSerializableExtra("booking");
            int bookingId = intent.getIntExtra("bookingId", -1);
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Booking booking = dbHelper.getBookingById( bookingId);


            // Populate views with booking details
            if (booking != null) {
                bookingIdTextView.setText("Booking ID: " + booking.getBookingId());
                bookingDateTextView.setText("Booking Date: " + booking.getBookingDate());
                flightTextView.setText("Flight: " + booking.getFlight().getFlightName() + " - " + booking.getFlight().getAirlineName());
                routeTextView.setText("Route: " + booking.getFlight().getSource() + " to " + booking.getFlight().getDestination());
                departureArrivalTextView.setText("Departure/Arrival: " + booking.getFlight().getDepartureDate() + " - " + booking.getFlight().getArrivalDate());
                passengerTextView.setText("Passenger: " + booking.getPassenger().getFirstName() + " " + booking.getPassenger().getLastName());
                phoneSeatTextView.setText("Phone/Seat: " + booking.getPassenger().getPhoneNumber() + " - " + booking.getSeatNumber());
                pricePaymentTextView.setText("Price/Payment: $" + booking.getPrice() + " - " + booking.getPaymentStatus());
            }
        }

        // Set click listeners for edit and delete buttons
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit action
                // You can start another activity for editing the booking
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete action
                // You can implement the logic to delete the booking here
            }
        });
    }
}
