package tests.users_actions;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.models.update_user.UpdateUserRequestBodyModel;
import tests.models.update_user.UpdateUserResponseBodyModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.helpers.CustomAllureListener.withCustomTemplates;
import static tests.specs.UpdateUserSpecs.updateUserRequestSpec;
import static tests.specs.UpdateUserSpecs.updateUserSuccessful200ResponseSpec;

public class UpdateUserTest extends TestBase {

    @Epic("Редактирование данных пользователя")
    @Story("Позитивный сценарий")
    @DisplayName("Успешное внесение изменений в данные пользователя")
    @Test
    void successfulUpdateUser200() {
        RestAssured.filters(withCustomTemplates());

        UpdateUserRequestBodyModel requestBody = new UpdateUserRequestBodyModel();
        requestBody.setName("morpheus");
        requestBody.setJob("zion resident");

        UpdateUserResponseBodyModel updateUserResponseBodyModel = step("Запрос на редактирование пользователя", () ->
                given()
                        .spec(updateUserRequestSpec)
                        .body(requestBody)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(updateUserSuccessful200ResponseSpec)
                        .extract().as(UpdateUserResponseBodyModel.class));

        step("Проверка ответа", () -> {
            assertEquals("morpheus", updateUserResponseBodyModel.getName());
            assertEquals("zion resident", updateUserResponseBodyModel.getJob());
        });
    }
}
