package com.example.goflight;


public class Booking {
    private int bookingId;
    private String bookingDate;

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    private String seatNumber;
    private String paymentStatus;

    private Flight flight;

    private Passenger passenger;

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Booking() {

    }

    public Booking( String bookingDate, String seatNumber, String paymentStatus, int price, String userName) {

        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
        this.price = price;
        this.userName = userName;
    }

    public Booking( String bookingDate, String seatNumber, String paymentStatus, String userName) {

        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
        this.userName = userName;
    }

    public Booking( String bookingDate, String seatNumber, String paymentStatus, Flight flight, Passenger passenger, int price, String userName) {
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
        this.flight = flight;
        this.passenger = passenger;
        this.price = price;
        this.userName = userName;
    }

    // Constructor
    public Booking(String bookingDate, String seatNumber, String paymentStatus) {
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
    }

    public Booking(String bookingDate, String seatNumber, String paymentStatus, int price) {
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
        this.price=price;
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }


    // Setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }



//    @Override
//    public String toString() {
//        return "Booking{" +
//                "bookingId=" + bookingId +
//                ", bookingDate='" + bookingDate + '\'' +
//                ", seatNumber='" + seatNumber + '\'' +
//                ", paymentStatus=" + paymentStatus +
//                '}';
//    }


    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate='" + bookingDate + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", price=" + price +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
