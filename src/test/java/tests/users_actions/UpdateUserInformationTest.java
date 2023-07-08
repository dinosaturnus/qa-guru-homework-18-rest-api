package tests.users_actions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class UpdateUserInformationTest {

    @Tag("PUT")
    @Tag("200")
    @DisplayName("Успешнеое внесение изменений в данные пользователя")
    @Test
    void successfulUpdateUser200() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/update-user-information-successful-put-200.json"))
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }
}
