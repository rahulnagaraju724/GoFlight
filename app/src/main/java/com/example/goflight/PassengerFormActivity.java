package com.example.goflight;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PassengerFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);

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

            // Display Passenger details
            Toast.makeText(PassengerFormActivity.this, passenger.toString(), Toast.LENGTH_LONG).show();
        });
    }
}
