package context;

import io.restassured.response.Response;

public class ScenarioContext {

    private static String token;
    private static Integer bookingId;
    private static Response response;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        ScenarioContext.token = token;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        ScenarioContext.bookingId = bookingId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        ScenarioContext.response = response;
    }
}