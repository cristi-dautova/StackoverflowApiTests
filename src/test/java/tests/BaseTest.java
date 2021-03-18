package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jsonobjects.Root;

import static io.restassured.RestAssured.given;
import static tests.UrlConstants.STACKOVERFLOW_PARAMETERS_URL;

public class BaseTest {

    private static RequestSpecification requestSpecification;

    public BaseTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(STACKOVERFLOW_PARAMETERS_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }

    public Response getResponse(String url) {
        return given(requestSpecification).when().get(url);
    }

    public Root deserializeRootResponse(String url) {
        return getResponse(url)
                .then()
                .extract()
                .as(Root.class);
    }
}