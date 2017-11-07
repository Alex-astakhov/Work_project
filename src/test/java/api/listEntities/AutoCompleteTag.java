package api.listEntities;

import assertion.Assertion;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.listEntities.AutoCompleteTagData;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

/**
 * Created by Asta on 13.07.2017.
 */
@Features("API get tags fot autocomplete")
public class AutoCompleteTag extends RetrofitClient {
    private String[] queries = {"водитель", "java", "php", "бухгалтер", "администратор", "директор", "дизайнер"};

    @Test
    public void autocompleteTagSuccessful() throws IOException {
        String query = DataGenerator.randomItemFromArray(queries);
        System.out.println("Try query: " + query);
        MainResponse<AutoCompleteTagData> response = apiService.autoCompleteTag(query).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.validate(), "response is not valid!");
    }

    @Test
    public void autocompleteTagEmptyQuery() throws IOException {
        MainResponse<AutoCompleteTagData> response = apiService.autoCompleteTag("").execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.data.tags.isEmpty(), "Tags list is not empty!");
    }

    @Test
    public void autocompleteTagWrongQuery() throws IOException {
        MainResponse<AutoCompleteTagData> response = apiService.autoCompleteTag("qwerty").execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.data.tags.isEmpty(), "Tags list is not empty!");
    }
}
