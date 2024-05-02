package com.example.goflight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class UserBookingsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        // Get the current user
        FirebaseUser currentUser = mAuth.getCurrentUser();

        System.out.println("Inside User Bookings");

        if (currentUser != null) {
            // User is signed in
            String userEmail = currentUser.getEmail();
            Toast.makeText(this, "Current User Email: " + userEmail, Toast.LENGTH_SHORT).show();

            dbHelper = new DatabaseHelper(this);
            List<Booking> bookings=dbHelper.getBookingsByUserName(currentUser.getEmail());
//            List<Booking> bookings=dbHelper.getAllBookings();
            System.out.println("Current username is:"+currentUser.getEmail()+", ended.");
            System.out.println("Found Bookings");
            for(Booking booking:bookings){
                System.out.println(booking.toString());
            }
            System.out.println("after Bookings print");

            // Display bookings in RecyclerView
            BookingAdapter bookingAdapter = new BookingAdapter(bookings);
            bookingAdapter.setOnItemClickListener(new BookingAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Booking booking) {
                    // Handle item click
                    Intent intent = new Intent(UserBookingsActivity.this, BookingDetailsActivity.class);
                    intent.putExtra("bookingId", booking.getBookingId());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(bookingAdapter);

            System.out.println("After adapter");

        } else {
            // No user is signed in
            Toast.makeText(this, "No user signed in", Toast.LENGTH_SHORT).show();
        }




    }
        
}
