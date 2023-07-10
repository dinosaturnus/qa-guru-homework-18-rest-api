package tests.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUserSpecs {

    public static RequestSpecification getUserRequestSpec = with()
            .log().uri()
            .log().body();

    public static ResponseSpecification getUserSuccessful200ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemes/getting-information-about-the-single-user-successful-get-200.json"))
            .build();

    public static ResponseSpecification getUserUnsuccessful404ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .expectBody(Matchers.anything())
            .build();

}
