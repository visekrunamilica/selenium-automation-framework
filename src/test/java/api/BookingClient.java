package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response getBookings() {
        return given()
                .when()
                .get("/booking");
    }

    public Response getBookingById(int bookingId) {
        return given()
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}");
    }

    public Response createBooking(Booking booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
    }

    public Response updateBooking(int bookingId, String token, Booking booking) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(booking)
                .when()
                .put("/booking/{id}");
    }
}
