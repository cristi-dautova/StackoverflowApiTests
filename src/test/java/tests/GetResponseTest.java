package tests;

import jsonobjects.Item;
import jsonobjects.Owner;
import jsonobjects.Root;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static tests.UrlConstants.STACKOVERFLOW_PARAMETERS_URL;

public class GetResponseTest extends BaseTest {

    @Test()
    public void getResponseHasCorrectAttributes() {

        SoftAssert softAssert = new SoftAssert();
        Root newRoot = deserializeRootResponse(STACKOVERFLOW_PARAMETERS_URL);

        softAssert.assertTrue(newRoot.backoff <= 10, "Item array size is incorrect: " + newRoot.backoff);

        for (Item item : newRoot.items) {
            Owner owner = item.owner;
            softAssert.assertNotNull(owner, "Item doesn't contain owner");
            if (owner == null) {
                continue;
            }
            softAssert.assertTrue(owner.link.contains("/" + owner.user_id + "/"), owner.link + " vs. " + owner.user_id);
            softAssert.assertTrue(owner.link.endsWith("/" + owner.display_name.replace(" ", "-").toLowerCase()), owner.link + " vs. " + owner.display_name);
        }
        softAssert.assertAll();
    }
}
