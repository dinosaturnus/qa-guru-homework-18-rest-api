package tests.users_actions;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends TestBase {

    @Tag("DELETE")
    @Tag("204")
    @DisplayName("Успешное удаление пользователя")
    @Test
    void successfulDeleteUser204() {
        given()
                .log().uri()
                .log().body()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204)
                .body(Matchers.anything());
    }
}
