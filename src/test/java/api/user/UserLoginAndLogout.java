package api.user;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.DataProviders;
import dataModels.apiModels.v1.EmptyData;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.user.ApplicantLoginData;
import http.APIService;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

/**
 * Created by Asta on 12.07.2017.
 */
@Features("API user login and logout")
public class UserLoginAndLogout {
    private APIService apiService;

    private String token;
    public static MainResponse<ApplicantLoginData> loginApi(String regId, String email, String password){
        APIService apiService = RetrofitClient.getClient(Constants.DOMEN);
        try {
            MainResponse<ApplicantLoginData> response = apiService.userLogin(regId, email, password).execute().body();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MainResponse<>();
    }

    @Test
    @Parameters({"stage"})
    public void initialization(@Optional("true") boolean stage){
        BrowserFactory.initDomen(stage);
        apiService = RetrofitClient.getClient(Constants.DOMEN);
    }

    @Test(dependsOnMethods = "initialization")
    public void verifySuccessLogin() throws IOException {
        MainResponse<ApplicantLoginData> response = loginApi(Constants.API_REG_ID, Constants.APPLICANT_API_TEST_EMAIL, Constants.PASSWORD);
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.data.token != null, "TOKEN IS NULL!");
        token = response.data.token;
        Assert.assertTrue(response.data.userID != null, "USER_ID IS NULL!");
    }

    @Test(dependsOnMethods = "initialization")
    public void verifyLoginWithoutRegId() throws IOException {
        MainResponse<ApplicantLoginData> response = loginApi("",Constants.APPLICANT_API_TEST_EMAIL, Constants.PASSWORD);
        Assertion.assertApiFailureResponse(response, 997);
    }

    @Test(dataProvider = "wrongLoginData", dataProviderClass = DataProviders.class, dependsOnMethods = "initialization")
    public void verifyLoginFailure(String email, String password) throws IOException {
        MainResponse<ApplicantLoginData> response = loginApi(Constants.API_REG_ID,email, password);
        Assertion.assertApiFailureResponse(response, 998);
    }

    @Test(dependsOnMethods = "verifySuccessLogin")
    public void verifySuccessLogout() throws IOException {
        MainResponse<EmptyData> response = apiService.userLogout(Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
    }

    @Test(dependsOnMethods = "verifySuccessLogout")
    public void verifyLogoutWithoutRegId() throws IOException {
        MainResponse<EmptyData> response = apiService.userLogout("", token, 1).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }

    @Test(dependsOnMethods = "verifySuccessLogout")
    public void verifyLogoutWithWrongToken() throws IOException {
        MainResponse<EmptyData> response = apiService.userLogout(Constants.API_REG_ID, token + "qwerty", 1).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }
}
