package api.listEntities;

import assertion.Assertion;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.listEntities.GetCitiesData;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

/**
 * Created by Asta on 13.07.2017.
 */
@Features("API get cities list")
public class GetAllCities extends RetrofitClient {

    @Test
    public void getCities() throws IOException {
        MainResponse<GetCitiesData> response = apiService.getCities().execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.validate(), "Response is not valid!");
    }

}
