package com.example.goflight;

public class Flight {
    private int flightId;
    private String source;
    private String destination;
    private String flightName;
    private String airlineName;
    private String departureDate;
    private String arrivalDate;
    private double price;

    public Flight() {
        // Default constructor
    }

    public Flight(int flightId, String source, String destination, String flightName, String airlineName, String departureDate, String arrivalDate, double price) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.flightName = flightName;
        this.airlineName = airlineName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", flightName='" + flightName + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", price=" + price +
                '}';
    }

    public void setPrice(double price) {
        this.price = price;
    }
}