package api.listEntities;

import assertion.Assertion;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.listEntities.AllStaticData;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

/**
 * Created by Asta on 13.07.2017.
 */
@Features("API get cities list")
public class AllStatic extends RetrofitClient {

    @Test
    public void allStatic() throws IOException {
        MainResponse<AllStaticData> response = apiService.allStatic().execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.validate());
    }

}
