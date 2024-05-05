package com.example.goflight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;





public class BookingDetailsActivity extends AppCompatActivity {

    private Booking booking;
    private EditText seatNumberEditText; // Input field for editing seat number

    int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set OnClickListener to the toolbar
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home page
                Intent intent = new Intent(BookingDetailsActivity.this, HomeScreenActivity.class);
                startActivity(intent);
//                finish(); // Optional: finish the current activity to prevent going back to it when pressing back
            }
        });

//        Button homeButton = findViewById(R.id.homeButton);
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create an Intent to navigate back to the home screen (replace HomeActivity.class with the actual home activity)
//                Intent intent = new Intent(BookingDetailsActivity.this, HomeScreenActivity.class);
//                // Add any extra data or flags if needed
//                // For example, you can use intent.putExtra("key", "value") to pass data to the home activity
//
//                // Start the home activity
//                startActivity(intent);
//
//                // Optionally, finish the current activity to remove it from the back stack
////                finish();
//            }
//        });


        DatabaseHelper dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bookingId")) {
//            booking = (Booking) intent.getSerializableExtra("booking");
            bookingId = intent.getIntExtra("bookingId", -1);
            Booking booking = dbHelper.getBookingById( bookingId);
            // Populate views with booking data
            populateBookingDetails(booking);
            }


        Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });
    }

    private void populateBookingDetails(Booking booking) {
        // Populate views with booking details
        TextView bookingIdTextView = findViewById(R.id.text_booking_id);
        bookingIdTextView.setText("Booking ID: " + booking.getBookingId());

        TextView bookingDateTextView = findViewById(R.id.text_booking_date);
        bookingDateTextView.setText("Booking Date: " + booking.getBookingDate());

        TextView flightTextView = findViewById(R.id.text_flight);
        flightTextView.setText("Flight: " + booking.getFlight().getFlightName() + " - " + booking.getFlight().getAirlineName());

        TextView routeTextView = findViewById(R.id.text_route);
        routeTextView.setText("Route: " + booking.getFlight().getSource() + " to " + booking.getFlight().getDestination());

        TextView departureArrivalTextView = findViewById(R.id.text_departure_arrival);
        departureArrivalTextView.setText("Departure/Arrival: " + booking.getFlight().getDepartureDate() + " - " + booking.getFlight().getArrivalDate());

        TextView passengerTextView = findViewById(R.id.text_passenger);
        passengerTextView.setText("Passenger: " + booking.getPassenger().getFirstName() + " " + booking.getPassenger().getLastName());

        TextView phoneSeatTextView = findViewById(R.id.text_phone_seat);
        phoneSeatTextView.setText("Phone/Seat: " + booking.getPassenger().getPhoneNumber() + " - " + booking.getSeatNumber());

        TextView pricePaymentTextView = findViewById(R.id.text_price_payment);
        pricePaymentTextView.setText("Price/Payment: $" + booking.getPrice() + " - " + booking.getPaymentStatus());
    }


    private void showEditDialog() {
        // Show a dialog to allow the user to input the desired seat number
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Booking");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_seat_number, null);
        builder.setView(dialogView);

        seatNumberEditText = dialogView.findViewById(R.id.edit_seat_number);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Validate input
                String newSeatNumber = seatNumberEditText.getText().toString().trim();
                if (!newSeatNumber.isEmpty()) {
                    // Update the booking with the new seat number
                    DatabaseHelper dbHelper = new DatabaseHelper(BookingDetailsActivity.this);
                    dbHelper.updateBookingSeatNumber(bookingId, newSeatNumber);

                    // Redirect to view the updated booking details
                    redirectToUpdatedBooking();
                } else {
                    Toast.makeText(BookingDetailsActivity.this, "Please enter a valid seat number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    private void redirectToUpdatedBooking() {
        // Redirect to view the updated booking details
        Intent intent = new Intent(BookingDetailsActivity.this, BookingDetailsActivity.class);
        intent.putExtra("bookingId", bookingId);
        startActivity(intent);
        finish(); // Finish this activity to prevent going back to it from the updated details activity
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this booking?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the booking
                deleteBooking();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void deleteBooking() {
        // Delete the booking
        // Redirect to UserBookingsActivity
        // Example:
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.deleteBookingById(bookingId);
        Intent intent = new Intent(BookingDetailsActivity.this, UserBookingsActivity.class);
        startActivity(intent);
        finish(); // Finish this activity to prevent going back to it from the user bookings activity
    }
}
