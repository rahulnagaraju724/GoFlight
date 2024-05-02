package com.example.goflight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goflight.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private static List<Booking> bookings;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Booking booking);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public BookingAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        // Bind booking data to the views in the ViewHolder
        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        private TextView bookingIdTextView;
        private TextView bookingDateTextView;
        private TextView flightTextView;
        private TextView routeTextView;
        private TextView departureArrivalTextView;
        private TextView passengerTextView;
        private TextView phoneSeatTextView;
        private TextView pricePaymentTextView;

        public BookingViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            // Initialize TextViews
            bookingIdTextView = itemView.findViewById(R.id.text_booking_id);
            bookingDateTextView = itemView.findViewById(R.id.text_booking_date);
            flightTextView = itemView.findViewById(R.id.text_flight);
            routeTextView = itemView.findViewById(R.id.text_route);
            departureArrivalTextView = itemView.findViewById(R.id.text_departure_arrival);
            passengerTextView = itemView.findViewById(R.id.text_passenger);
            phoneSeatTextView = itemView.findViewById(R.id.text_phone_seat);
            pricePaymentTextView = itemView.findViewById(R.id.text_price_payment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(bookings.get(position));
                        }
                    }
                }
            });
        }

        public void bind(Booking booking) {
            // Bind booking data to TextViews
            bookingIdTextView.setText("Booking ID: " + booking.getBookingId());
            bookingDateTextView.setText("Booking Date: " + booking.getBookingDate());
            flightTextView.setText("Flight: " + booking.getFlight().getFlightName() + " - " + booking.getFlight().getAirlineName());
            routeTextView.setText("Route: " + booking.getFlight().getSource() + " to " + booking.getFlight().getDestination());
            departureArrivalTextView.setText("Departure/Arrival: " + booking.getFlight().getDepartureDate() + " - " + booking.getFlight().getArrivalDate());
            passengerTextView.setText("Passenger: " + booking.getPassenger().getFirstName()+" "+booking.getPassenger().getLastName());
            phoneSeatTextView.setText("Phone/Seat: " + booking.getPassenger().getPhoneNumber() + " - " + booking.getSeatNumber());
            pricePaymentTextView.setText("Price/Payment: $" + booking.getPrice() + " - " + booking.getPaymentStatus());
        }
    }
}
