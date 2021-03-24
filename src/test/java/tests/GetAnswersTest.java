package tests;

import jsonobjects.Root;
import jsonobjects.answers_questions.Item;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.UrlConstants.ANSWERS_END_POINT_WITH_BASE_PARAMETERS;

public class GetAnswersTest extends BaseTest {

    @Test()
    public void getAnswers() {
        String url = String.format(ANSWERS_END_POINT_WITH_BASE_PARAMETERS, "1", "10");
        Root rootResponse = deserializeResponseWithoutParams(url);
        softAssert.assertTrue(rootResponse.getItems().size() <= 10, "Item array size is incorrect: " + rootResponse.getBackoff());
        verifyUserLink(rootResponse, softAssert);
        softAssert.assertAll();
    }

    private void verifyUserLink(Root rootResponse, SoftAssert softAssert) {
        for (Item item : rootResponse.getItems()) {
            softAssert.assertNotNull(item.getOwner(), "Item doesn't contain owner");
            softAssert.assertTrue(item.getOwner().getLink().contains("/" + item.getOwner().getUser_id() + "/"),
                    item.getOwner().getLink() + " vs. " + item.getOwner().getUser_id());
            softAssert.assertTrue(item.getOwner().getLink().endsWith("/" + item.getOwner().getDisplay_name().replace(" ", "-").toLowerCase()),
                    item.getOwner().getLink() + " vs. " + item.getOwner().getDisplay_name());
        }
    }
}



