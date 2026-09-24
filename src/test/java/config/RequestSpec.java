package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

        private RequestSpec() {
        }

        public static RequestSpecification getRequestSpec() {

                String baseUrl = ConfigManager.getBaseUrl();

                System.out.println("BASE URL CONFIG: " + baseUrl);

                return new RequestSpecBuilder()
                                .setBaseUri(baseUrl)
                                .setContentType(ContentType.JSON)
                                .setConfig(
                                                io.restassured.RestAssured.config()
                                                                .sslConfig(
                                                                                SSLConfig.sslConfig()
                                                                                                .relaxedHTTPSValidation()))
                                .build();
        }
}