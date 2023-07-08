package tests.users_actions;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class TestBase {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}

