package tests;

import jsonobjects.Root;
import jsonobjects.answers_questions.Item;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Scanner;

import static utils.UrlComposer.composeURL;
import static utils.UrlConstants.*;

public class GetQuestionsIdsAnswersTest extends BaseTest {

    @BeforeMethod
    public void readQuestionsIds(){
        Scanner scanner = new Scanner(fileName);
        scanner.useDelimiter(";");
        while(scanner.hasNext(";")){
            System.out.println(scanner.next(";"));
        }
    }

    @Test(dataProvider = "Path parameters for getQuestionIdsAnswersTest URL")
    public void getQuestionIds(String ids) {

        /* Make GET request with end points /questions/{ids}/answers
         * Expected result:
         *       1) item contains owner field
         *       2) item contains question id field
         *       3) item contains answer id field
         *       4) question id value(s) equals to the id value(s) in request
         * */

        String url = composeURL(QUESTIONS_END_POINT, IDS_PATH_PARAMETER, ANSWERS_END_POINT);
        Root rootResponse = getAnswersByQuestionsId(url, ids);

        if (rootResponse.getItems().size() == 0) {
            System.out.println("There is no question with id = " + Integer.parseInt(ids));
        } else {
            for (Item item : rootResponse.getItems()){
                softAssert.assertNotNull(item.getOwner(), "Item doesn't contain owner");
                softAssert.assertEquals(item.getQuestion_id(), Integer.parseInt(ids),"Question id = " + item.getQuestion_id());
                softAssert.assertNotNull(item.getAnswer_id(), "Item doesn't contain Answer id");
            }
        }
        softAssert.assertAll();
    }
}



