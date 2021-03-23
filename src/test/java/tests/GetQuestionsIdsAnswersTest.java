package tests;

import jsonobjects.Root;
import jsonobjects.answers_questions.Item;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.UrlComposer.composeURL;
import static utils.UrlConstants.*;

public class GetQuestionsIdsAnswersTest extends BaseTest {

    private String url;

    @BeforeTest
    public void goToUrl(){
        url = composeURL(QUESTIONS_END_POINT, IDS_PATH_PARAMETER, ANSWERS_END_POINT);
    }

    @Test(dataProvider = "Parameters for getQuestionIdAnswers")
    public void getQuestionId(String id) {

        /* Make GET request with end points /questions/{ids}/answers
         * Expected result:
         *       1) item contains owner field
         *       2) item contains question id field
         *       3) item contains answer id field
         *       4) question id value equals to the id value in request
         * */

        Root rootResponse = getAnswersByQuestionId(url, id);

        for (Item item : rootResponse.getItems()){
            softAssert.assertNotNull(item.getOwner(), "Item doesn't contain owner");
            softAssert.assertEquals(item.getQuestion_id(), Integer.parseInt(id),"Question id = " + item.getQuestion_id());
            softAssert.assertNotNull(item.getAnswer_id(), "Item doesn't contain Answer id");
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "Parameters for getQuestionIdsAnswers")
    public void getQuestionIds(String ids) {

        /* Make GET request with end points /questions/{ids}/answers
         * Expected result:
         *       1) question id values in response equal to the id values in request
         * */

        Root rootResponse = getAnswersByQuestionsId(url, ids);
        String[] singleId = ids.split(";");

        Set<Integer> setIdsFromPath = new HashSet<>(Arrays.asList(singleId).stream().map(Integer::parseInt).collect(Collectors.toList()));
        Set<Integer> setIdsFromJson = new HashSet<>(rootResponse.getItems().stream().map(e -> e.getQuestion_id()).collect(Collectors.toList()));

        softAssert.assertTrue(setIdsFromPath.equals(setIdsFromJson), "Ids from path: " + setIdsFromPath + " are not equal to ids from json: " + setIdsFromJson);
        softAssert.assertAll();
    }
}



