package com.example.goflight;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PassengerFormActivity extends AppCompatActivity {

    private Flight selectedFlight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);


        // Receive selected flight details from intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("flight")) {
            selectedFlight = intent.getParcelableExtra("flight");
        }

//        System.out.println(selectedFlight.toString());
        int flight_id=intent.getIntExtra("flight_id",-1);
        System.out.println("flight_id in passaneger form" + flight_id);

        // Initialize EditText fields
        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etLastName = findViewById(R.id.etLastName);
        EditText etMobileNumber = findViewById(R.id.etMobileNumber);
        EditText etNationality = findViewById(R.id.etNationality);
        EditText etPassportNumber = findViewById(R.id.etPassportNumber);
        EditText etDateOfBirth = findViewById(R.id.etDateOfBirth);

        // Initialize Submit Button
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Set OnClickListener for Submit Button
        btnSubmit.setOnClickListener(view -> {
            // Read input values from EditText fields
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String mobileNumber = etMobileNumber.getText().toString().trim();
            String nationality = etNationality.getText().toString().trim();
            String passportNumber = etPassportNumber.getText().toString().trim();
            String dateOfBirth = etDateOfBirth.getText().toString().trim();

            // Create Passenger object
            Passenger passenger = new Passenger(firstName, lastName, mobileNumber, nationality, dateOfBirth, passportNumber);

            Booking booking=new Booking("2024-04-20 08:00","25 D","true",225);


            System.out.println(passenger.toString());
            System.out.println(booking.toString());

            // Save booking to the database (Assuming you have a method to save booking in DatabaseHelper)
            DatabaseHelper dbHelper = new DatabaseHelper(PassengerFormActivity.this);
            long passengerId=dbHelper.savePassenger(passenger);

//            long bookingId = dbHelper.saveBooking(booking);
//
//            if (bookingId != -1) {
//                // Display booking details
//                Toast.makeText(PassengerFormActivity.this, "Booking successful! Booking ID: " + bookingId, Toast.LENGTH_LONG).show();
//                // You can display more booking details here if needed
//            } else {
//                Toast.makeText(PassengerFormActivity.this, "Failed to book the flight. Please try again.", Toast.LENGTH_SHORT).show();
//            }
        });
    }
}
