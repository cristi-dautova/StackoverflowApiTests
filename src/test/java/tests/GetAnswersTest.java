package tests;

import io.restassured.response.Response;
import jsonobjects.answers_questions.Item;
import jsonobjects.Root;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.util.ArrayList;

import static utils.UrlComposer.*;
import static utils.UrlConstants.*;

public class GetAnswersTest extends BaseTest {

    ArrayList<Integer> questionsId;

    @Test(dataProvider = "Parameters for getAnswersTest URL")
    public void getAnswers(String page, String pageSize, String pageValue, String pagesizeValue) {
        String[] parameterKeys = {page, pageSize};
        String[] parameterValues = {pageValue, pagesizeValue};
        String url = composeURL(parameterKeys, parameterValues, ANSWERS_END_POINT);

        Response response = getResponse(url);
        Root rootResponse = getAnswers(response);

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(rootResponse.getItems().size() <= Integer.parseInt(pagesizeValue), "Item array size is incorrect: " + rootResponse.getBackoff());
        verifyUserLink(rootResponse, softAssert);
        questionsId = getQuestionIds(rootResponse);
        softAssert.assertAll();
    }

    @AfterMethod
    public void writeData() throws IOException {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
        } catch (FileNotFoundException e) {
            new File(fileName);
        }
        for(int id : questionsId){
            fileWriter.write(id + ";");
        }
        fileWriter.close();
    }

    public ArrayList<Integer> getQuestionIds(Root rootResponse) {
        questionsId = new ArrayList<>();
        for (Item item : rootResponse.getItems()) {
            questionsId.add(item.getQuestion_id());
        }
        return questionsId;
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



