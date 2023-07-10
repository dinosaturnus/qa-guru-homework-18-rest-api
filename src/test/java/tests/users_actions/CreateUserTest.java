package tests.users_actions;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.models.create_user.CreateUserRequestBodyModel;
import tests.models.create_user.CreateUserResponseBodyModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.helpers.CustomAllureListener.withCustomTemplates;
import static tests.specs.CreateUserSpecs.*;

public class CreateUserTest extends TestBase {

    @Epic("Создание пользователя")
    @Story("Позитивный сценарий")
    @DisplayName("Успешное создание нового пользователя")
    @Test
    void successfulCreateNewUser201() {
        RestAssured.filters(withCustomTemplates());

        CreateUserRequestBodyModel requestBody = new CreateUserRequestBodyModel();
        requestBody.setName("morpheus");
        requestBody.setJob("leader");

        CreateUserResponseBodyModel createUserResponseBodyModel =
                step("Запрос на создание пользователя", () ->
                        given()
                                .spec(createUserRequestSpec)
                                .body(requestBody)
                                .when()
                                .post("/users")
                                .then()
                                .spec(createUserSuccessful201ResponseSpec)
                                .extract().as(CreateUserResponseBodyModel.class));

        step("Проверка ответа", () -> {
            assertEquals("morpheus", createUserResponseBodyModel.getName());
            assertEquals("leader", createUserResponseBodyModel.getJob());
        });
    }

}
