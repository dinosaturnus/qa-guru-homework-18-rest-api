package tests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class DeleteUserSpecs {

    public static RequestSpecification deleteUserRequestSpec = with()
            .log().uri()
            .log().body();

    public static ResponseSpecification deleteUserSuccessful204ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .expectBody(Matchers.anything())
            .build();
}
