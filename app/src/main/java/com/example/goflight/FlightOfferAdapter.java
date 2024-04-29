package com.example.goflight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightOfferAdapter extends RecyclerView.Adapter<FlightOfferAdapter.ViewHolder> {

    private List<FlightOffer> flightOffers;

    public void setFlightOffers(List<FlightOffer> flightOffers) {
        this.flightOffers = flightOffers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlightOffer flightOffer = flightOffers.get(position);
        holder.bind(flightOffer);
    }

    @Override
    public int getItemCount() {
        return flightOffers != null ? flightOffers.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView airlineTextView;
        private TextView offerTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            airlineTextView = itemView.findViewById(R.id.airline_text_view);
            offerTextView = itemView.findViewById(R.id.offer_text_view);
        }

        void bind(FlightOffer flightOffer) {
            airlineTextView.setText(flightOffer.getAirline());
            offerTextView.setText(flightOffer.getOffer());
        }
    }
}
