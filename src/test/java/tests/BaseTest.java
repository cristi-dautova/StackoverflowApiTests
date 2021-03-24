package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jsonobjects.Root;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static utils.UrlConstants.BASE_URL;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.METHOD);
        requestSpecBuilder.log(LogDetail.URI);
        requestSpecification = requestSpecBuilder.build();
    }


    public Root deserializeResponseWithoutParams(String url) {
        return given(requestSpecification).when().get(url)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK).log().body()
                .extract()
                .as(Root.class);
    }

    public Root deserializeResponseWithParams(String url, String parameterName, String parameterValue) {
        return given(requestSpecification).pathParams(parameterName, parameterValue).when().get(url)
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    public Root deserializeResponseWithParam(String url, String parameterName, String parameterValues) {
        return given(requestSpecification).pathParam(parameterName, parameterValues).when().get(url)
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    @BeforeMethod
    protected void createSoftAssert() {
        softAssert = new SoftAssert();
    }
}