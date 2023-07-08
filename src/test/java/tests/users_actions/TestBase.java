package tests.users_actions;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public abstract class TestBase {

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}

