package tests;

import io.restassured.response.Response;
import jsonobjects.answers.Item;
import jsonobjects.answers.Owner;
import jsonobjects.Root;
import org.testng.annotations.Test;
import utils.UrlComposer;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static utils.UrlConstants.*;

public class GetResponseTest extends BaseTest {

    @Test(dataProvider = "parameters for answersEndPointTest")
    public void test (String data, String second) {
        System.out.println(data + " " + second);
    }

    @Test()
    public void getAnswersEndPointAndVerifyResponse() {

        ArrayList<String> parameterKeys = UrlComposer.setParametersToUrl("page", "pagesize");
        ArrayList<String> parameterValues = UrlComposer.setParametersToUrl("1", "10");
        String url = UrlComposer.composeURL(parameterKeys, parameterValues, ANSWERS_END_POINT);

        Response response = getResponse(url);
        Root root = deserializeResponse(response);

        softAssert.assertTrue(root.getBackoff() <= 10, "Item array size is incorrect: " + root.getBackoff());

        for (Item item : root.getItems()) {
            Owner owner = item.getOwner();
            softAssert.assertNotNull(owner, "Item doesn't contain owner");
            if (owner == null) {
                continue;
            }
            softAssert.assertTrue(owner.getLink().contains("/" + owner.getUser_id() + "/"), owner.getLink() + " vs. " + owner.getUser_id());
            softAssert.assertTrue(owner.getLink().endsWith("/" + owner.getDisplay_name().replace(" ", "-").toLowerCase()),
                    owner.getLink() + " vs. " + owner.getDisplay_name());
        }
        softAssert.assertAll();
    }

    @Test()
    public void getQuestionsEndPointAndVerifyResponse() {

        String questionIdAnswerEndPoint = UrlComposer.composeURL(QUESTIONS_END_POINT, IDS_PATH_PARAMETER, ANSWERS_END_POINT);

        Root newRoot = given(requestSpecification).pathParam("ids", "126;34").when().get(questionIdAnswerEndPoint)
                .then()
                .extract()
                .as(Root.class);

    }


}
