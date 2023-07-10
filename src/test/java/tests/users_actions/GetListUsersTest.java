package tests.users_actions;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.models.get_list_users.GetListUsersResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.helpers.CustomAllureListener.withCustomTemplates;
import static tests.specs.GetListUsersSpecs.getListUsersRequestSpec;
import static tests.specs.GetListUsersSpecs.getListUsersResponseSuccessful200Spec;


public class GetListUsersTest extends TestBase {

    @Epic("Получение списка пользователей")
    @Story("Позитивный сценарий")
    @DisplayName("Получение списка пользователей на заданной странице")
    @Test
    void successfulListUsers200() {
        RestAssured.filters(withCustomTemplates());
        Integer page = 2;

        GetListUsersResponseModel getListUsersResponseModel = step("Запрос на получение списка пользователей", () ->
                given()
                        .spec(getListUsersRequestSpec)
                        .when()
                        .get("/users?page=" + page)
                        .then()
                        .spec(getListUsersResponseSuccessful200Spec)
                        .extract().as(GetListUsersResponseModel.class));

        step("Проверка ответа", () -> {
            assertEquals(page, getListUsersResponseModel.getPage());
            assertEquals(12, getListUsersResponseModel.getTotal());
            assertEquals(9, getListUsersResponseModel.getData().get(2).getId());
            assertEquals("tobias.funke@reqres.in", getListUsersResponseModel.getData().get(2).getEmail());
            assertEquals("Tobias", getListUsersResponseModel.getData().get(2).getFirstName());
            assertEquals("Funke", getListUsersResponseModel.getData().get(2).getLastName());
            assertEquals("https://reqres.in/img/faces/9-image.jpg",
                    getListUsersResponseModel.getData().get(2).getAvatar());
        });
    }
}
