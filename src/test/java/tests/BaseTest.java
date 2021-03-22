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

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static utils.UrlConstants.*;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    protected SoftAssert softAssert;
    protected String fileName = "D:/questionIdsData.txt";

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

    public Root getAnswers(Response response) {
        return response
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    public Root getAnswersByQuestionsId(String url, String ids) {
        return given(requestSpecification).pathParam("ids", ids).when().get(url)
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    @BeforeMethod
    protected void createSoftAssert() {
        softAssert = new SoftAssert();
    }

    @DataProvider(name = "Parameters for getAnswersTest URL")
    public Object[][] dataProviderForGetAnswersTest() {
        return new Object[][] { {"page", "pagesize", "1", "10"} };
    }

    @DataProvider(name = "Path parameters for getQuestionIdsAnswersTest URL")
    public Object[][] dataProviderForQuestionIdsAnswersTest() {
        return new Object[][] { {"126"} };
    }
}