package com.example.goflight;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<Flight> flights;

    // Constructor to initialize the flight list
    public FlightAdapter() {
        this.flights = new ArrayList<>();
    }

    // Method to set the list of flights
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    // ViewHolder class to hold the views for each flight item
    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView airlineNameTextView;
        TextView flightNumberTextView;
        TextView departureDetailsTextView;
        TextView arrivalDetailsTextView;
        TextView priceTextView;

        ImageView airlineLogoImageView;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            airlineNameTextView = itemView.findViewById(R.id.airline_name_text_view);
            flightNumberTextView = itemView.findViewById(R.id.flight_number_text_view);
            departureDetailsTextView = itemView.findViewById(R.id.departure_details_text_view);
            arrivalDetailsTextView = itemView.findViewById(R.id.arrival_details_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            airlineLogoImageView=itemView.findViewById(R.id.airline_logo_image_view);
        }
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_card_item, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flights.get(position);

        // Bind flight data to views
        holder.airlineNameTextView.setText(flight.getAirlineName());
        holder.flightNumberTextView.setText(flight.getFlightName());
        holder.departureDetailsTextView.setText(flight.getDepartureDate());
        holder.arrivalDetailsTextView.setText(flight.getArrivalDate());
        holder.priceTextView.setText(String.valueOf(flight.getPrice()));

        // Load the image resource directly to the ImageView
        holder.airlineLogoImageView.setImageResource(R.drawable.img_1); // Assuming img_1 is your image resource

        // Set click listener for each flight item
        holder.itemView.setOnClickListener(view -> {
            // Start PassengerFormActivity and pass selected flight details as intent extras
            Intent intent = new Intent(view.getContext(), PassengerFormActivity.class);
            intent.putExtra("flight", String.valueOf(flight));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
}
