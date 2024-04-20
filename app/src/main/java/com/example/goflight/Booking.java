package com.example.goflight;


public class Booking {
    private int bookingId;
    private String bookingDate;
    private String seatNumber;
    private boolean paymentStatus;

    public Booking() {

    }

    // Constructor
    public Booking(int bookingId, String bookingDate, String seatNumber, boolean paymentStatus) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
    }

    public Booking(String bookingDate, String seatNumber, boolean paymentStatus) {
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.paymentStatus = paymentStatus;
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

    public boolean isPaymentStatus() {
        return paymentStatus;
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

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate='" + bookingDate + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
