package com.example.goflight;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "flight_booking.db";
    public static final int DATABASE_VERSION = 1;

    // Flight table
    public static final String TABLE_FLIGHT = "flight";
    public static final String COLUMN_FLIGHT_ID = "flight_id";
    public static final String COLUMN_SOURCE = "source";
    public static final String COLUMN_DESTINATION = "destination";
    public static final String COLUMN_FLIGHT_NAME = "flight_name";
    public static final String COLUMN_AIRLINE_NAME = "airline_name";
    public static final String COLUMN_DEPARTURE_DATE = "departure_date";
    public static final String COLUMN_ARRIVAL_DATE = "arrival_date";
    public static final String COLUMN_PRICE = "price";

    // Passenger table
    public static final String TABLE_PASSENGER = "passenger";
    public static final String COLUMN_PASSENGER_ID = "passenger_id";
    public static final String COLUMN_PASSENGER_NAME = "passenger_name";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    public static final String COLUMN_NATIONALITY = "nationality";
    public static final String COLUMN_PASSPORT = "passport";
    public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";

    // Booking table
    public static final String TABLE_BOOKING = "booking";
    public static final String COLUMN_BOOKING_ID = "booking_id";
    public static final String COLUMN_BOOKING_DATE = "booking_date";
    public static final String COLUMN_SEAT_NUMBER = "seat_number";
    public static final String COLUMN_PAYMENT_STATUS = "payment_status";

    // Create table queries
    private static final String CREATE_TABLE_FLIGHT = "CREATE TABLE " + TABLE_FLIGHT + "("
            + COLUMN_FLIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SOURCE + " TEXT,"
            + COLUMN_DESTINATION + " TEXT,"
            + COLUMN_FLIGHT_NAME + " TEXT,"
            + COLUMN_AIRLINE_NAME + " TEXT,"
            + COLUMN_DEPARTURE_DATE + " TEXT,"
            + COLUMN_ARRIVAL_DATE + " TEXT,"
            + COLUMN_PRICE + " REAL"
            + ")";

    private static final String CREATE_TABLE_PASSENGER = "CREATE TABLE " + TABLE_PASSENGER + "("
            + COLUMN_PASSENGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PASSENGER_NAME + " TEXT,"
            + COLUMN_FIRST_NAME + " TEXT,"
            + COLUMN_LAST_NAME + " TEXT,"
            + COLUMN_MOBILE_NUMBER + " TEXT,"
            + COLUMN_NATIONALITY + " TEXT,"
            + COLUMN_PASSPORT + " TEXT,"
            + COLUMN_DATE_OF_BIRTH + " TEXT"
            + ")";

    private static final String CREATE_TABLE_BOOKING = "CREATE TABLE " + TABLE_BOOKING + "("
            + COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BOOKING_DATE + " TEXT,"
            + COLUMN_PASSENGER_ID + " INTEGER,"
            + COLUMN_FLIGHT_ID + " INTEGER,"
            + COLUMN_SEAT_NUMBER + " TEXT,"
            + COLUMN_PRICE + " REAL,"
            + COLUMN_PAYMENT_STATUS + " TEXT,"
            + "FOREIGN KEY(" + COLUMN_PASSENGER_ID + ") REFERENCES " + TABLE_PASSENGER + "(" + COLUMN_PASSENGER_ID + "),"
            + "FOREIGN KEY(" + COLUMN_FLIGHT_ID + ") REFERENCES " + TABLE_FLIGHT + "(" + COLUMN_FLIGHT_ID + ")"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_FLIGHT);
        db.execSQL(CREATE_TABLE_PASSENGER);
        db.execSQL(CREATE_TABLE_BOOKING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSENGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHT);
        // Create tables again
        onCreate(db);
    }




    public List<Flight> searchFlights(String source, String destination, String departureDate) {
        List<Flight> flights = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase(); // Obtain a reference to the writable database

        // Perform database query to search for flights
        // Construct your SQL query here based on the search criteria and execute it using SQLiteDatabase

        // Example:
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_FLIGHT +
                " WHERE " + DatabaseHelper.COLUMN_SOURCE + " = ?" +
                " AND " + DatabaseHelper.COLUMN_DESTINATION + " = ?" +
                " AND " + DatabaseHelper.COLUMN_DEPARTURE_DATE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{source, destination, departureDate});

        // Iterate over the cursor and create Flight objects
        if (cursor.moveToFirst()) {
            do {
                Flight flight = new Flight();
                flight.setFlightId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_FLIGHT_ID)));
                flight.setSource(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SOURCE)));
                flight.setDestination(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESTINATION)));
                flight.setFlightName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FLIGHT_NAME)));
                flight.setAirlineName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AIRLINE_NAME)));
                flight.setDepartureDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DEPARTURE_DATE)));
                flight.setArrivalDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ARRIVAL_DATE)));
                flight.setPrice(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRICE)));
                flights.add(flight);
            } while (cursor.moveToNext());
        }

        // Close cursor after use
        cursor.close();

        return flights;
    }
}