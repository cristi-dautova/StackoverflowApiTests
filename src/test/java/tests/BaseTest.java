package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jsonobjects.Root;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static utils.UrlConstants.BASE_URL;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    protected SoftAssert softAssert;
    protected String fileName = "D:/questionIdsData.csv";

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
        return given(requestSpecification).pathParams("ids", ids).when().get(url)
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    public Root getAnswersByQuestionId(String url, String id) {
        return given(requestSpecification).pathParam("ids", id).when().get(url)
                .then().log().body()
                .extract()
                .as(Root.class);
    }

    @BeforeMethod
    protected void createSoftAssert() {
        softAssert = new SoftAssert();
    }

    @DataProvider(name = "Parameters for getAnswers")
    public Object[][] dataProviderForGetAnswers() {
        return new Object[][]{{"page", "pagesize", "1", "10"}};
    }

    @DataProvider(name = "Parameters for getQuestionIdAnswers")
    public Object[][] dataProviderForQuestionIdAnswersTest() throws FileNotFoundException {
        String[] singleIds = readDataFromFile(fileName).split(";");
        return new Object[][]{{singleIds[0]}};
    }

    @DataProvider(name = "Parameters for getQuestionIdsAnswers")
    public Object[][] dataProviderForQuestionIdsAnswersTest() throws FileNotFoundException {
        return new Object[][]{{readDataFromFile(fileName)}};
    }

    public String readDataFromFile(String fileName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        return StringUtils.chop(line);
    }
}