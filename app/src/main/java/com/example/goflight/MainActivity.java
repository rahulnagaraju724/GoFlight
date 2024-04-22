package com.example.goflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static DatabaseHelper dbHelper;
    private static SQLiteDatabase db;

    private EditText sourceInput, destinationInput, departureDateInput;
    private Button searchFlightButton;
    private FlightAdapter flightAdapter;
    private View recyclerView;

    private FirebaseAuth mAuth;
    private View emailEditText;
    private View passwordEditText;
    private View loginButton;
    private Object view;
    private TextInputEditText departureDateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        FirebaseApp.initializeApp(this);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        // Find emailEditText and passwordEditText views by their IDs
//        emailEditText = findViewById(R.id.emailEditText);
//        passwordEditText = findViewById(R.id.passwordEditText);
//
//        // Cast emailEditText and passwordEditText to EditText
//        EditText emailInput = (EditText) emailEditText;
//        EditText passwordInput = (EditText) passwordEditText;
//
//        loginButton = findViewById(R.id.loginButton);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailInput.getText().toString();
//                String password = passwordInput.getText().toString();
//
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Toast.makeText(MainActivity.this, "Authentication successful.",
//                                            Toast.LENGTH_SHORT).show();
//                                    // Proceed to the next activity or perform desired action
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Toast.makeText(MainActivity.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });

//
//        ManualFlightDataGenerator manualFlightDataGenerator=new ManualFlightDataGenerator(this);
//        manualFlightDataGenerator.generateManualFlightData();

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase(); // This will create or open the database for writing

        // Find EditText views by their IDs
        sourceInput = findViewById(R.id.source_input);
        destinationInput = findViewById(R.id.destination_input);
        departureDateInput = findViewById(R.id.departure_date_input);
        // Find Button view by its ID
        searchFlightButton = findViewById(R.id.search_flight_button);


        departureDateEditText = findViewById(R.id.departure_date_input);





//        // Retrieve input values and store them in variables when the button is clicked
//        searchFlightButton.setOnClickListener(view -> {
//            String source = sourceInput.getText().toString();
//            String destination = destinationInput.getText().toString();
//            String departureDate = departureDateInput.getText().toString();
//            System.out.println("Source:"+source+" Destination:"+destination+" and departureDate:"+departureDate);
//            List<Flight> flights=dbHelper.searchFlights(source,destination,departureDate);
//            for(Flight flight:flights){
//                System.out.println("Inside new flight");
//                System.out.println(flight.toString());
//            }
//
//            // Update RecyclerView with new flight list
//            flightAdapter.setFlights(flights);
//            flightAdapter.notifyDataSetChanged();
//        });

        searchFlightButton.setOnClickListener(view -> {
            String source = sourceInput.getText().toString();
            String destination = destinationInput.getText().toString();
            String departureDate = departureDateInput.getText().toString();

            System.out.println("Source:"+source+" Destination:"+destination+" and departureDate:"+departureDate);

            // Start FlightSearchResultsActivity and pass search parameters as intent extras
            Intent intent = new Intent(MainActivity.this, FlightSearchResultsActivity.class);
            intent.putExtra("source", source);
            intent.putExtra("destination", destination);
            intent.putExtra("departureDate", departureDate);
            startActivity(intent);
        });


    }



    public static SQLiteDatabase getDatabase() {
        return db;
    }

    public void showDatePicker(View view) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (datePicker, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    // Set selected date to TextInputEditText

                    String selectedMonthStr;
                    if(selectedMonth+1<10){
                        selectedMonthStr="0"+Integer.toString(selectedMonth + 1);
                    }else{
                        selectedMonthStr=Integer.toString(selectedMonth + 1);
                    }

                    String selectedDayOfMonthStr;
                    if(selectedDayOfMonth<10){
                        selectedDayOfMonthStr="0"+Integer.toString(selectedDayOfMonth);
                    }else{
                        selectedDayOfMonthStr=Integer.toString(selectedDayOfMonth);
                    }

                    String selectedDate = selectedYear + "-" +selectedMonthStr +  "-" + selectedDayOfMonthStr ;
                    departureDateEditText.setText(selectedDate);
                },
                year, month, dayOfMonth);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

}