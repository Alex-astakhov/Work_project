package api.user;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import core.Email;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.EmptyData;
import dataModels.apiModels.v1.MainResponse;
import http.APIService;
import http.RetrofitClient;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Features;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Asta on 12.07.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("API restoring password")
public class ForgotPassword extends BrowserFactory{
    private APIService apiService;
    private String token;
    private Date dateOfAction;

    @Test
    @Parameters({"stage"})
    public void initialization(@Optional("true") boolean stage){
        dateOfAction = DataGenerator.getCurrentDate(-30);
        BrowserFactory.initDomen(stage);
        apiService = RetrofitClient.getClient(Constants.DOMEN);
    }

    @Test(dependsOnMethods = "initialization")
    public void login(){
        token = UserLoginAndLogout.loginApi(Constants.API_REG_ID, Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD).data.token;
        Assert.assertTrue(token != null, "Token = null!");
    }

    @Test(dependsOnMethods = "login")
    public void verifySendingRestoreLinkToEmail() throws IOException {
        MainResponse<EmptyData> response = apiService.userForgotPassword(Constants.APPLICANT_LOGIN_EMAIL).execute().body();
        Assertion.assertApiSuccessResponse(response);
    }

    @Test(dependsOnMethods = "verifySendingRestoreLinkToEmail")
    public void verifyEmail() throws MessagingException {
        Email email;
        if (Constants.DOMEN.contains("dev"))
            email = new Email(Constants.DEV_EMAIL, Constants.DEV_PASSWORD);
        else
            email = new Email(Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD);
        String text = Email.getLetterBody(email.getLastLetterFromGmail("Восстановление пароля", Constants.TIMEOUT_FOR_EMAIL, dateOfAction), 1);
        String confirmUrl = email.getUrlFromEmailContent(text, "/restore/");
        System.out.println("Confirm URL: " + confirmUrl);
        get(confirmUrl);
        Assertion.urlContains("user/restore/", 5, "Password restore page wasn't shown!");
    }

    @AfterClass
    public void logout() throws IOException {
        apiService.userLogout(Constants.API_REG_ID, token, 1).execute();
    }
}
