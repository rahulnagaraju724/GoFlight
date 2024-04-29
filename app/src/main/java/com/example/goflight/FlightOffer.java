package com.example.goflight;

public class FlightOffer {

    private String airline;
    private String offer;

    public FlightOffer(String airline, String offer) {
        this.airline = airline;
        this.offer = offer;
    }

    public String getAirline() {
        return airline;
    }

    public String getOffer() {
        return offer;
    }
}
