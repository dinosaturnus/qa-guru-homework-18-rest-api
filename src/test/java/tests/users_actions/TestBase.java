package tests.users_actions;

import com.codeborne.selenide.Configuration;

public class TestBase {

    static void beforeAll() {
        Configuration.baseUrl = "https://reqres.in";
    }
}
