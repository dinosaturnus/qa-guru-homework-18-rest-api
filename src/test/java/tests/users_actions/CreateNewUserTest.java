package tests.users_actions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CreateNewUserTest extends TestBase {

    @Tag("POST")
    @Tag("201")
    @DisplayName("Успешное создание нового пользователя")
    @Test
    void successfulCreateNewUser201() {

        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemes/create-new-user-successful-post-201.json"))
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

}
