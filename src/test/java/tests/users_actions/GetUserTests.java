package tests.users_actions;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.models.get_user.GetUserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.helpers.CustomAllureListener.withCustomTemplates;
import static tests.specs.GetUserSpecs.*;

public class GetUserTests extends TestBase {

    @Epic("Получение информации о пользователе")
    @Story("Позитивный сценарий")
    @DisplayName("Получение информации о существующем пользователе")
    @Test
    void successfulSingleUser200() {
        RestAssured.filters(withCustomTemplates());

        GetUserResponseModel getUserResponseModel = step("Запрос на получение информации о пользователе", () ->
                given()
                        .spec(getUserRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(getUserSuccessful200Response)
                        .extract().as(GetUserResponseModel.class));

        step("Проверка ответа", () -> {
            assertEquals(2, getUserResponseModel.getData().getId());
            assertEquals("janet.weaver@reqres.in", getUserResponseModel.getData().getEmail());
            assertEquals("Janet", getUserResponseModel.getData().getFirstName());
            assertEquals("Weaver", getUserResponseModel.getData().getLastName());
            assertEquals("https://reqres.in/img/faces/2-image.jpg", getUserResponseModel.getData().getAvatar());
        });
    }

    @Epic("Получение информации о пользователе")
    @Story("Негативные сценарии")
    @Tag("GET")
    @DisplayName("Получение информации о несуществующем пользователе")
    @Test
    void unsuccessfulSingleUserDoesNotExist404() {
        RestAssured.filters(withCustomTemplates());

        step("Запрос на получение информации о пользователе", () ->
                given()
                        .spec(getUserRequestSpec)
                        .when()
                        .get("/users/0")
                        .then()
                        .spec(getUserUnsuccessful404Response)
        );
    }
}

