package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingResponse {

    @JsonProperty("bookingid")
    private int bookingId;

    private Booking booking;

    public CreateBookingResponse() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public Booking getBooking() {
        return booking;
    }
}
