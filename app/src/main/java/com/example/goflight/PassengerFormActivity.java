package com.example.goflight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PassengerFormActivity extends AppCompatActivity {

    private Flight selectedFlight;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set OnClickListener to the toolbar
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home page
                Intent intent = new Intent(PassengerFormActivity.this, HomeScreenActivity.class);
                startActivity(intent);
//                finish(); // Optional: finish the current activity to prevent going back to it when pressing back
            }
        });

        mAuth = FirebaseAuth.getInstance();
        // Get the current user
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String userEmail;

        if (currentUser != null) {
            // User is signed in
             userEmail= currentUser.getEmail();
            Toast.makeText(this, "Current User Email: " + userEmail, Toast.LENGTH_SHORT).show();
        } else {
            userEmail = "";
            // No user is signed in
            Toast.makeText(this, "No user signed in", Toast.LENGTH_SHORT).show();
        }
        
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

            System.out.println("Emial"+ currentUser.getEmail());
            Booking booking=new Booking(LocalDate.now().toString(),selectRandomSeat(),"Paid", currentUser.getEmail());


            System.out.println("Prininting username: "+userEmail);
            booking.setUserName(userEmail);

            System.out.println(passenger.toString());
            System.out.println(booking.toString());

            // Save booking to the database (Assuming you have a method to save booking in DatabaseHelper)
            DatabaseHelper dbHelper = new DatabaseHelper(PassengerFormActivity.this);

            long passengerId=dbHelper.savePassenger(passenger);
            System.out.println("Passenger Id: "+passengerId);

            Flight selectedFlight = dbHelper.getFlightById(flight_id);
            Passenger newPassenger=dbHelper.getPassengerById((int)passengerId);

            booking.setPassenger(newPassenger);
            booking.setFlight(selectedFlight);
            booking.setPrice((int) selectedFlight.getPrice());

            System.out.println("After saving flight and passenger in booking:" + booking.toString());
            System.out.println("Seleted flight"+selectedFlight.toString());
            System.out.println("Selected Passenger"+newPassenger.toString());


            long bookingId = dbHelper.saveBooking(booking);

            if (bookingId != -1) {
                // Display booking details
                Toast.makeText(PassengerFormActivity.this, "Booking successful! Booking ID: " + bookingId, Toast.LENGTH_LONG).show();
                System.out.println("After saving in DB"+dbHelper.getBookingById((int) bookingId).toString());

                Intent confirmationIntent = new Intent(PassengerFormActivity.this, BookingConfirmationActivity.class);
                confirmationIntent.putExtra("bookingId", bookingId);
                confirmationIntent.putExtra("flightId", flight_id);
                confirmationIntent.putExtra("passengerId", passengerId);
                startActivity(confirmationIntent);

                // You can display more booking details here if needed
            } else {
                Toast.makeText(PassengerFormActivity.this, "Failed to book the flight. Please try again.", Toast.LENGTH_SHORT).show();
            }



        });
    }

    public static List<String> generateSeatNumbers(int numberOfSeats) {
        List<String> seatNumbers = new ArrayList<>();
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int rowLength = rows.length;
        for (int i = 1; i <= numberOfSeats; i++) {
            int rowIndex = (i - 1) / rowLength;
            char rowLetter = rows[rowIndex];
            int seatNumber = (i - 1) % rowLength + 1;
            seatNumbers.add(seatNumber + String.valueOf(rowLetter));
        }
        return seatNumbers;
    }

    public static String selectRandomSeat() {
        List<String> seatNumbers=generateSeatNumbers(10);
        Random random = new Random();
        int index = random.nextInt(seatNumbers.size());
        return seatNumbers.get(index);
    }
}
