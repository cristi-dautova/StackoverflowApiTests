package tests;

import jsonobjects.Root;
import jsonobjects.answers_questions.Item;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.UrlConstants.ANSWERS_END_POINT_WITH_BASE_PARAMETERS;
import static utils.UrlConstants.QUESTIONS_IDS_ANSWERS_END_POINT_WITH_BASE_PARAMETERS;

public class GetQuestionsIdsAnswersTest extends BaseTest {

    private List<Integer> questionIds;

    @BeforeClass
    public void getQuestionsIdsFromResponse() {
        String url = String.format(ANSWERS_END_POINT_WITH_BASE_PARAMETERS, "1", "10");
        Root rootResponse = deserializeResponseWithoutParams(url);
        questionIds = rootResponse.getItems().stream().map(Item::getQuestion_id).collect(Collectors.toList());
    }

    @Test()
    public void getQuestionId() {
        /* Make GET request with end points /questions/{ids}/answers
         * Expected result:
         *       1) item contains owner field
         *       2) item contains question id field
         *       3) question id value equals to the id value in request
         * */
        Integer questionId = questionIds.get(0);
        Root rootResponse = deserializeResponseWithParam(QUESTIONS_IDS_ANSWERS_END_POINT_WITH_BASE_PARAMETERS, "ids", questionId.toString());

        for (Item item : rootResponse.getItems()) {
            softAssert.assertNotNull(item.getOwner(), "Item doesn't contain owner");
            softAssert.assertEquals(item.getQuestion_id(), (int) questionId, "Question id = " + item.getQuestion_id());
        }
        softAssert.assertAll();
    }

    @Test()
    public void getQuestionIds() {
        /* Make GET request with end points /questions/{ids}/answers
         * Expected result:
         *       1) question id values in response equal to the id values in request
         * */
        String idsForURL = StringUtils.join(questionIds, ";");
        Root rootResponse = deserializeResponseWithParams(QUESTIONS_IDS_ANSWERS_END_POINT_WITH_BASE_PARAMETERS, "ids", idsForURL);

        Set<Integer> setIdsFromPath = new HashSet<>(questionIds);
        Set<Integer> setIdsFromJson = rootResponse.getItems().stream().map(Item::getQuestion_id).collect(Collectors.toSet());

        softAssert.assertTrue(setIdsFromPath.equals(setIdsFromJson), "Ids from path: " + setIdsFromPath + " are not equal to ids from json: " + setIdsFromJson);
        softAssert.assertAll();
    }
}



