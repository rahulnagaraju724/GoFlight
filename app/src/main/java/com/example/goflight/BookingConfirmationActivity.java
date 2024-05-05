package com.example.goflight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_confirmation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set OnClickListener to the toolbar
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home page
                Intent intent = new Intent(BookingConfirmationActivity.this, HomeScreenActivity.class);
                startActivity(intent);
//                finish(); // Optional: finish the current activity to prevent going back to it when pressing back
            }
        });

        // Retrieve booking, flight, and passenger IDs from intent extras
        Intent intent = getIntent();
        long bookingId = intent.getLongExtra("bookingId", -1);
        int flightId = intent.getIntExtra("flightId", -1);
        long passengerId = intent.getLongExtra("passengerId", -1);

        // Assuming you have methods to retrieve booking, flight, and passenger details from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Booking booking = dbHelper.getBookingById((int) bookingId);
        Flight flight = dbHelper.getFlightById(flightId);
        Passenger passenger = dbHelper.getPassengerById((int) passengerId);

        // Set the text of the TextViews with the retrieved data
        TextView textBookingConfirmation = findViewById(R.id.text_booking_confirmation);
        textBookingConfirmation.setText("Booking Confirmed!");

        TextView textFlightName = findViewById(R.id.text_flight_name);
        textFlightName.setText("Flight Name: " + flight.getFlightName());

        TextView textFlightPath = findViewById(R.id.text_flight_path);
        textFlightPath.setText("Flying from " + flight.getSource() + " to " + flight.getDestination());

        TextView textDates = findViewById(R.id.text_dates);
        textDates.setText("Departure: " + flight.getDepartureDate() + " | Arrival: " + flight.getArrivalDate());

        TextView textPrice = findViewById(R.id.text_price);
        textPrice.setText("Total Price: $" + booking.getPrice());

        TextView textPassengerName = findViewById(R.id.text_passenger_name);
        textPassengerName.setText("Passenger Name: " + passenger.getFirstName() + " " + passenger.getLastName());

        TextView textSeatNumber = findViewById(R.id.text_seat_number);
        textSeatNumber.setText("Seat: " + booking.getSeatNumber());

        TextView textPaymentStatus = findViewById(R.id.text_payment_status);
        textPaymentStatus.setText("Payment Status: " + booking.getPaymentStatus());


        Button userBookingsButton = findViewById(R.id.userBookings);
        userBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to user bookings page
                startActivity(new Intent(BookingConfirmationActivity.this, UserBookingsActivity.class));
            }
        });

    }

}
