package clients;

import config.RequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response consultarBookings() {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .get("/booking");
    }

    public Response consultarBookingPorId(Integer bookingId) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}");
    }

    public Response criarBooking(String body) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(body)
                .when()
                .post("/booking");
    }

    public Response atualizarBooking(
            Integer bookingId,
            String token,
            String body) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(body)
                .log().all()
                .when()
                .put("/booking/{id}")
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response atualizarBookingParcial(Integer bookingId,
            String token,
            String body) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(body)
                .when()
                .patch("/booking/{id}");
    }

    public Response excluirBooking(Integer bookingId, String token) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .cookie("token", token)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}");
    }

}
