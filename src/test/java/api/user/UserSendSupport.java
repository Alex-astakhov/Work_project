package api.user;

import assertion.Assertion;
import core.Constants;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.EmptyData;
import dataModels.apiModels.v1.MainResponse;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

/**
 * Created by Asta on 12.07.2017.
 */
@Features("API sending to support")
public class UserSendSupport extends RetrofitClient {
    private String text = "Тестовое обращение в поддержку от " + DataGenerator.getCurrentDateAndTimeString();

    @Test
    public void login(){
        token = UserLoginAndLogout.loginApi(Constants.API_REG_ID, Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD).data.token;
        Assert.assertTrue(token != null, "Token = null!");
    }

    @Test(dependsOnMethods = "login")
    public void verifySuccessSendSupport() throws IOException {
        MainResponse<EmptyData> response = apiService.userSendSupport(text, Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
    }

    @Test(dependsOnMethods = "login")
    public void verifySendSupportWithoutText() throws IOException {
        MainResponse<EmptyData> response = apiService.userSendSupport("", Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiFailureResponse(response, 998);
    }

    @Test(dependsOnMethods = "login")
    public void verifySendSupportWithWrongToken() throws IOException {
        MainResponse<EmptyData> response = apiService.userSendSupport(text, Constants.API_REG_ID, token + "qwerty", 1).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }
}
