package tests.users_actions;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class GettingInformationAboutTheUserTests extends TestBase {

    @Tag("GET")
    @Tag("200")
    @DisplayName("Успешное получение информации о существующем пользователе")
    @Test
    void successfulSingleUser200() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/getting-information-about-the-single-user-successful-get-200.json"))
                .body("data.id", equalTo(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"));
    }


    @Tag("GET")
    @Tag("404")
    @DisplayName("Получение информации о несуществующем пользователе")
    @Test
    void unsuccessfulSingleUserDoesNotExist404() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/users/0")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body(Matchers.anything());
    }
}

