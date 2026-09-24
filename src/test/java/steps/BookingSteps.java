package steps;

import clients.BookingClient;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.JsonUtils;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookingSteps {

        private final BookingClient bookingClient = new BookingClient();
        private final ScenarioContext context = new ScenarioContext();

        private String body;

        @Given("que possuo acesso a API de bookings")
        public void acessoApi() {
                body = JsonUtils.readJson(
                                "payloads/booking/createBooking.json");
        }

        @Given("que existe um booking cadastrado")
        public void criarBookingCapturarID() {
                body = JsonUtils.readJson(
                                "payloads/booking/createBooking.json");

                Response response = bookingClient.criarBooking(body);

                Integer bookingId = response.jsonPath().getInt("bookingid");

                context.setBookingId(bookingId);

                System.out.println(
                                "Booking criado: " + bookingId);
        }

        @When("realizo a consulta de todos os bookings")
        public void consultarBookings() {
                Response response = bookingClient.consultarBookings();

                context.setResponse(response);
        }

        @When("realizo a consulta de booking por id")
        public void consultarBookingsPorID() {
                Integer bookingId = context.getBookingId();

                Response response = bookingClient.consultarBookingPorId(
                                bookingId);

                context.setResponse(response);
        }

        @When("realizo o cadastro de um novo booking")
        public void criarBooking() {
                Response response = bookingClient.criarBooking(body);

                context.setResponse(response);
        }

        @When("realizo a atualizacao do booking")
        public void atualizarBooking() {
                String body = JsonUtils.readJson(
                                "payloads/booking/updateBooking.json");

                Integer bookingId = context.getBookingId();

                String token = context.getToken();

                System.out.println(
                                "TOKEN: " + token);

                System.out.println(
                                "BOOKING ID: " + bookingId);

                Response response = bookingClient.atualizarBooking(
                                bookingId,
                                token,
                                body);

                context.setResponse(response);
        }

        @When("realizo a atualizacao parcial do booking")
        public void atualizarBookingParcial() {
                String body = JsonUtils.readJson(
                                "payloads/booking/partialUpdateBooking.json");

                Integer bookingId = context.getBookingId();

                String token = context.getToken();

                Response response = bookingClient.atualizarBookingParcial(
                                bookingId,
                                token,
                                body);

                context.setResponse(response);
        }

        @When("realizo a exclusao do booking")
        public void realizo_a_exclusao_do_booking() {
                Integer bookingId = context.getBookingId();

                String token = context.getToken();

                Response response = bookingClient.excluirBooking(
                                bookingId, token);

                context.setResponse(response);
        }

        @Then("a API deve retornar status code 200")
        public void validarStatusCode200() {
                context.getResponse()
                                .then()
                                .statusCode(200);
        }

        @Then("a API deve retornar status code 201")
        public void validarStatusCode201() {
                context.getResponse()
                                .then()
                                .statusCode(201);
        }

        @Then("deve retornar uma lista de bookings")
        public void validarListaBookings() {
                List<Integer> bookings = context.getResponse()
                                .jsonPath()
                                .getList(
                                                "bookingid",
                                                Integer.class);

                assertFalse(bookings.isEmpty());

                System.out.println(
                                "Quantidade de bookings: "
                                                + bookings.size());

                System.out.println(
                                bookings.subList(
                                                0,
                                                Math.min(
                                                                10,
                                                                bookings.size())));
        }

        @Then("deve retornar o booking do ID mencionado")
        public void validarBookingPorID() {
                context.getResponse()
                                .then()
                                .body(
                                                "firstname",
                                                notNullValue())
                                .body(
                                                "lastname",
                                                notNullValue())
                                .body(
                                                "totalprice",
                                                notNullValue())
                                .body(
                                                "depositpaid",
                                                notNullValue());
        }

        @Then("deve retornar o booking cadastrado")
        public void validarBookingCriado() {
                context.getResponse()
                                .then()
                                .statusCode(200)
                                .body(
                                                "bookingid",
                                                notNullValue())
                                .body(
                                                "booking.firstname",
                                                equalTo("Jim"))
                                .body(
                                                "booking.lastname",
                                                equalTo("Brown"));
        }

        @Then("deve retornar os dados atualizados")
        public void validarBookingAtualizado() {
                context.getResponse()
                                .then()
                                .body(
                                                "firstname",
                                                equalTo("James"))
                                .body(
                                                "lastname",
                                                equalTo("Brown"))
                                .body(
                                                "totalprice",
                                                equalTo(200));
        }

        @Then("deve retornar os dados parcialmente atualizados")
        public void validarBookingParcialAtualizado() {
                context.getResponse()
                                .then()
                                .body("firstname", equalTo("James"))
                                .body("lastname", equalTo("Brown"));
        }

        @Then("o booking nao deve mais existir")
        public void validarExclusao() {
                Integer bookingId = context.getBookingId();

                Response response = bookingClient.consultarBookingPorId(
                                bookingId);

                response.then()
                                .statusCode(404);
        }

}