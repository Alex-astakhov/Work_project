package api.user;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.user.GetLinkAutoLoginData;
import http.APIService;
import http.RetrofitClient;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

@Listeners({ListenerWithBrowserShot.class})
@Features("API get link for auto login")
public class GetLinkAutoLogin extends BrowserFactory {
    private APIService apiService;
    private String token;
    private String link;

    @Test
    @Parameters({"stage"})
    public void initialization(@Optional("true") boolean stage){
        BrowserFactory.initDomen(stage);
        apiService = RetrofitClient.getClient(Constants.DOMEN);
    }

    @Test(dependsOnMethods = "initialization")
    public void login(){
        token = UserLoginAndLogout.loginApi(Constants.API_REG_ID, Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD).data.token;
        Assert.assertTrue(token != null, "Token = null!");
    }

    @Test(dependsOnMethods = "login")
    public void getLinkAutoLogin() throws IOException {
        MainResponse<GetLinkAutoLoginData> response = apiService.getLinkAutoLogin(Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.validate());
        link = response.data.link;
    }

    @Test(dependsOnMethods = "getLinkAutoLogin")
    public void verifyLink(){
        System.out.println("Open URL: " + link);
        get(link);
        Assertion.urlContains("applicant/notebook", 10, "Wrong final URL!");
    }

    @AfterClass
    public void logout() throws IOException {
        apiService.userLogout(Constants.API_REG_ID, token, 1).execute();
    }
}
