package tests.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;
import models.BookingDates;
import models.CreateBookingResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingApiTest {

    @Test
    public void getBookingsReturnsSuccessfulResponse() {

        Response response = given().baseUri("https://restful-booker.herokuapp.com").when().get("/booking");
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Assert.assertFalse(bookingIds.isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getBookingByIdReturnsSuccessfulResponse() {

        Response response = given().baseUri("https://restful-booker.herokuapp.com").when().get("/booking");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Integer bookingId = bookingIds.get(0);

        Response bookingResponse = given().baseUri("https://restful-booker.herokuapp.com").pathParam("id", bookingId).when().get("/booking/{id}");

        Assert.assertEquals(bookingResponse.getStatusCode(), 200);

        String firstName = bookingResponse.jsonPath().getString("firstname");
        String checkIn = bookingResponse.jsonPath().getString("bookingdates.checkin");

        Assert.assertNotNull(firstName);
        Assert.assertFalse(firstName.isEmpty());
        Assert.assertNotNull(checkIn);
        Assert.assertFalse(checkIn.isEmpty());

    }

    @Test
    public void userCanCreateBooking() {

        BookingDates dates = new BookingDates("2026-09-10", "2026-09-15");

        Booking booking = new Booking("Milica", "Test", 150, true, dates, "Breakfast");

        Response response = given().baseUri("https://restful-booker.herokuapp.com").contentType(ContentType.JSON).body(booking).when().post("/booking");
        Assert.assertEquals(response.getStatusCode(), 200);

        CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
        int bookingId = createBookingResponse.getBookingId();
        Assert.assertEquals(createBookingResponse.getBooking().getFirstName(), booking.getFirstName());
        Assert.assertEquals(createBookingResponse.getBooking().getLastName(), booking.getLastName());

        Response getResponse = given().baseUri("https://restful-booker.herokuapp.com").pathParam("id", bookingId).when().get("/booking/{id}");
        Assert.assertEquals(getResponse.getStatusCode(), 200);
        Booking actualBooking = getResponse.as(Booking.class);
        Assert.assertEquals(actualBooking.getFirstName(), booking.getFirstName());
        Assert.assertEquals(actualBooking.getLastName(), booking.getLastName());
        Assert.assertEquals(actualBooking.getTotalPrice(), booking.getTotalPrice());
        Assert.assertEquals(actualBooking.getDepositPaid(), booking.getDepositPaid());
        Assert.assertEquals(actualBooking.getBookingDates().getCheckin(), booking.getBookingDates().getCheckin());
        Assert.assertEquals(actualBooking.getBookingDates().getCheckout(), booking.getBookingDates().getCheckout());
        Assert.assertEquals(actualBooking.getAdditionalNeeds(), booking.getAdditionalNeeds());
    }
}
