package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jsonobjects.Root;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static utils.UrlConstants.*;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    protected SoftAssert softAssert;

    public BaseTest() {
    }

    @BeforeClass
    public void setUp() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.METHOD);
        requestSpecBuilder.log(LogDetail.URI);
        requestSpecification = requestSpecBuilder.build();
    }

    public Response getResponse(String url) {
        return given(requestSpecification).when().get(url);
    }

    public Root deserializeResponse(Response response) {
        return response
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    @DataProvider(name = "parameters for answersEndPointTest")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "page", "pagesize" }, { "1", "10" } };
    }

    @BeforeMethod
    protected void createSoftAssert() {
        softAssert = new SoftAssert();
    }
}