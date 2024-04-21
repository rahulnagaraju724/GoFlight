package com.example.goflight;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class ManualFlightDataGenerator {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public ManualFlightDataGenerator(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void generateManualFlightData() {
        // Open database for writing
        database = dbHelper.getWritableDatabase();

        // Flight 1
            insertFlight("New York", "Los Angeles", "Delta", "DL101", "2024-04-20 08:00", "2024-04-20 11:00", 350.0);

        // Flight 2
        insertFlight("London", "Paris", "British Airways", "BA202", "2024-04-21 10:00", "2024-04-21 12:00", 200.0);

        // Flight 3
        insertFlight("Tokyo", "Sydney", "Qantas", "QF501", "2024-04-22 14:00", "2024-04-23 05:00", 800.0);

        // Flight 4
        insertFlight("Dubai", "Beijing", "Emirates", "EK888", "2024-04-23 12:00", "2024-04-23 18:00", 600.0);

        // Flight 5
        insertFlight("Moscow", "Singapore", "Singapore Airlines", "SQ777", "2024-04-24 15:00", "2024-04-25 05:00", 900.0);

        // Flight 6
        insertFlight("New York", "London", "Delta", "DL202", "2024-04-25 09:00", "2024-04-25 14:00", 450.0);

        // Flight 7
        insertFlight("Los Angeles", "Tokyo", "American Airlines", "AA505", "2024-04-26 11:00", "2024-04-27 08:00", 850.0);

        // Flight 8
            insertFlight("Paris", "Dubai", "Emirates", "EK303", "2024-04-27 13:00", "2024-04-27 19:00", 700.0);

        // Flight 9
        insertFlight("Sydney", "New York", "Qantas", "QF101", "2024-04-28 17:00", "2024-04-29 08:00", 950.0);

        // Flight 10
        insertFlight("Beijing", "Moscow", "Air China", "CA888", "2024-04-29 20:00", "2024-04-30 01:00", 500.0);

        // Flight 11
        insertFlight("Singapore", "Los Angeles", "Singapore Airlines", "SQ308", "2024-04-30 10:00", "2024-05-01 16:00", 1200.0);

        // Flight 12
        insertFlight("Paris", "New York", "Air France", "AF401", "2024-05-01 12:00", "2024-05-01 20:00", 750.0);

        // Flight 13
        insertFlight("Sydney", "London", "Qantas", "QF901", "2024-05-02 08:00", "2024-05-02 18:00", 1100.0);

        // Flight 14
        insertFlight("Dubai", "Tokyo", "Emirates", "EK511", "2024-05-03 15:00", "2024-05-04 02:00", 900.0);

        // Flight 15
        insertFlight("New York", "Singapore", "Singapore Airlines", "SQ101", "2024-05-04 11:00", "2024-05-05 21:00", 1300.0);

        // Flight 16
        insertFlight("Beijing", "Los Angeles", "Air China", "CA987", "2024-05-05 09:00", "2024-05-06 16:00", 1000.0);

        // Flight 17
        insertFlight("London", "Sydney", "British Airways", "BA709", "2024-05-06 14:00", "2024-05-07 05:00", 1400.0);

        // Flight 18
        insertFlight("Moscow", "Paris", "Aeroflot", "SU303", "2024-05-07 16:00", "2024-05-07 19:00", 600.0);

        // Flight 19
        insertFlight("Tokyo", "Beijing", "ANA", "NH108", "2024-05-08 10:00", "2024-05-08 12:00", 400.0);

        // Flight 20
        insertFlight("Los Angeles", "Dubai", "Emirates", "EK702", "2024-05-09 08:00", "2024-05-09 20:00", 1100.0);

        // Flight 21
        insertFlight("Singapore", "Moscow", "Singapore Airlines", "SQ368", "2024-05-10 12:00", "2024-05-11 02:00", 1000.0);

        // Flight 22
        insertFlight("New York", "Tokyo", "American Airlines", "AA701", "2024-05-11 14:00", "2024-05-12 06:00", 1200.0);

        // Flight 23
        insertFlight("Paris", "Singapore", "Air France", "AF162", "2024-05-12 16:00", "2024-05-13 06:00", 800.0);

        // Flight 24
        insertFlight("Dubai", "London", "Emirates", "EK5", "2024-05-13 18:00", "2024-05-14 00:00", 700.0);

        // Flight 25
        insertFlight("Sydney", "New York", "Qantas", "QF355", "2024-05-14 09:00", "2024-05-15 20:00", 1400.0);

        // Flight 26
        insertFlight("Los Angeles", "Beijing", "United", "UA888", "2024-05-15 11:00", "2024-05-16 17:00", 1100.0);

        // Flight 27
        insertFlight("London", "Moscow", "British Airways", "BA873", "2024-05-16 14:00", "2024-05-17 18:00", 900.0);

        // Flight 28
        insertFlight("Tokyo", "Paris", "ANA", "NH211", "2024-05-17 17:00", "2024-05-18 06:00", 1300.0);

        // Flight 29
        insertFlight("New York", "Dubai", "Emirates", "EK204", "2024-05-18 08:00", "2024-05-18 19:00", 1000.0);

        // Flight 30
        insertFlight("Singapore", "Sydney", "Singapore Airlines", "SQ231", "2024-05-19 10:00", "2024-05-19 20:00", 800.0);

        // Close the database connection
        database.close();
    }

    private void insertFlight(String source, String destination, String airline, String flightName, String departureDate, String arrivalDate, double price) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SOURCE, source);
        values.put(DatabaseHelper.COLUMN_DESTINATION, destination);
        values.put(DatabaseHelper.COLUMN_AIRLINE_NAME, airline);
        values.put(DatabaseHelper.COLUMN_FLIGHT_NAME, flightName);
        values.put(DatabaseHelper.COLUMN_DEPARTURE_DATE, departureDate);
        values.put(DatabaseHelper.COLUMN_ARRIVAL_DATE, arrivalDate);
        values.put(DatabaseHelper.COLUMN_PRICE, price);

//         Inserting row into flight table
        long newRowId = database.insert(DatabaseHelper.TABLE_FLIGHT, null, values);
    }
}
