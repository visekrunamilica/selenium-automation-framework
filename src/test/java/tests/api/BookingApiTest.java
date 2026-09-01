package tests.api;

import api.AuthClient;
import api.BookingClient;
import base.BaseApiTest;
import config.ConfigReader;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BookingApiTest extends BaseApiTest {

    BookingClient bookingClient = new BookingClient();
    AuthClient authClient = new AuthClient();

    @Test
    public void getBookingsReturnsSuccessfulResponse() {

        Response response = bookingClient.getBookings();
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Assert.assertFalse(bookingIds.isEmpty());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getBookingByIdReturnsSuccessfulResponse() {

        Response response = bookingClient.getBookings();

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Integer bookingId = bookingIds.get(0);

        Response bookingResponse = bookingClient.getBookingById(bookingId);

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

        Response response = bookingClient.createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 200);

        CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
        int bookingId = createBookingResponse.getBookingId();
        Assert.assertEquals(createBookingResponse.getBooking().getFirstName(), booking.getFirstName());
        Assert.assertEquals(createBookingResponse.getBooking().getLastName(), booking.getLastName());

        Response getResponse = bookingClient.getBookingById(bookingId);
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
    @Test
    public void userCanUpdateBooking() {

        Booking originalBooking = new Booking(
                "Milica",
                "Test",
                150,
                true,
                new BookingDates("2026-09-10", "2026-09-15"),
                "Breakfast"
        );

        Response createResponse = bookingClient.createBooking(originalBooking);

        CreateBookingResponse created =
                createResponse.as(CreateBookingResponse.class);

        int bookingId = created.getBookingId();

        AuthRequest authRequest = new AuthRequest(
                ConfigReader.get("apiUsername"),
                ConfigReader.get("apiPassword")
        );

        Response authResponse = authClient.authenticate(authRequest);

        AuthResponse auth =
                authResponse.as(AuthResponse.class);

        String token = auth.getToken();

        Booking updatedBooking = new Booking(
                "Milica",
                "Updated",
                250,
                false,
                new BookingDates("2026-10-01", "2026-10-05"),
                "Dinner"
        );

        Response updateResponse =
                bookingClient.updateBooking(bookingId, token, updatedBooking);

        Assert.assertEquals(updateResponse.getStatusCode(), 200);

        Response getResponse = bookingClient.getBookingById(bookingId);

        Assert.assertEquals(getResponse.getStatusCode(), 200);

        Booking actualBooking = getResponse.as(Booking.class);

        Assert.assertEquals(actualBooking.getFirstName(), updatedBooking.getFirstName());
        Assert.assertEquals(actualBooking.getLastName(), updatedBooking.getLastName());
        Assert.assertEquals(actualBooking.getTotalPrice(), updatedBooking.getTotalPrice());
        Assert.assertEquals(actualBooking.getDepositPaid(), updatedBooking.getDepositPaid());
        Assert.assertEquals(
                actualBooking.getBookingDates().getCheckin(),
                updatedBooking.getBookingDates().getCheckin()
        );
        Assert.assertEquals(
                actualBooking.getBookingDates().getCheckout(),
                updatedBooking.getBookingDates().getCheckout()
        );
        Assert.assertEquals(
                actualBooking.getAdditionalNeeds(),
                updatedBooking.getAdditionalNeeds()
        );

    }
}
