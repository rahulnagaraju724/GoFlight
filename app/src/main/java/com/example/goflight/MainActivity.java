package com.example.goflight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static DatabaseHelper dbHelper;
    private static SQLiteDatabase db;

    private EditText sourceInput, destinationInput, departureDateInput;
    private Button searchFlightButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ManualFlightDataGenerator manualFlightDataGenerator=new ManualFlightDataGenerator(this);
        manualFlightDataGenerator.generateManualFlightData();

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase(); // This will create or open the database for writing

        // Find EditText views by their IDs
        sourceInput = findViewById(R.id.source_input);
        destinationInput = findViewById(R.id.destination_input);
        departureDateInput = findViewById(R.id.departure_date_input);
        // Find Button view by its ID
        searchFlightButton = findViewById(R.id.search_flight_button);

        // Retrieve input values and store them in variables when the button is clicked
        searchFlightButton.setOnClickListener(view -> {
            String source = sourceInput.getText().toString();
            String destination = destinationInput.getText().toString();
            String departureDate = departureDateInput.getText().toString();
            System.out.println("Source:"+source+" Destination:"+destination+" and departureDate:"+departureDate);
            List<Flight> flights=dbHelper.searchFlights(source,destination,departureDate);
            for(Flight flight:flights){
                System.out.println("Inside new flight");
                System.out.println(flight.toString());
            }
        });

    }

    public static SQLiteDatabase getDatabase() {
        return db;
    }
}