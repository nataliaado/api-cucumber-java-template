package steps;

import clients.AuthClient;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utils.JsonUtils;

public class AuthSteps {

        private final ScenarioContext context = new ScenarioContext();
        private final AuthClient authClient = new AuthClient();
        private String body;

        @Given("que possuo credenciais validas")
        public void credenciaisValidas() {

                body = JsonUtils.readJson(
                                "payloads/auth/loginValido.json");
        }

        @Given("solicito a geracao do token")
        public void solicitarToken() {

                String body = JsonUtils.readJson(
                                "payloads/auth/loginValido.json");

                Response response = authClient.generateToken(body);

                String token = response.jsonPath().getString("token");

                context.setToken(token);
                context.setResponse(response);
        }

        @Then("o token deve ser gerado com sucesso")
        public void validarToken() {

                context.getResponse()
                                .then()
                                .statusCode(200);

                Assertions.assertNotNull(
                                context.getToken());
        }
}