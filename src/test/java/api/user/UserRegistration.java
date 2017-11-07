package api.user;

import assertion.Assertion;
import core.Constants;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.user.ApplicantRegistrationData;
import http.HttpActions;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

/**
 * Created by Asta on 11.07.2017.
 */

@Features("API user registrations")
public class UserRegistration extends RetrofitClient {
    private dataModels.applicant.ApplicantRegistrationData regData = new dataModels.applicant.ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, Constants.PASSWORD);

    @Test
    @Parameters({"stage"})
    public void initialization(){
        apiService = RetrofitClient.getClient(Constants.DOMEN);
    }


    @Test(dependsOnMethods = "initialization")
    public void verifySuccessRegistration() throws IOException {
        MainResponse<ApplicantRegistrationData> response = apiService.registerApplicant(Constants.API_REG_ID, regData.getEmail(),
                regData.getPassword(), regData.getFirstName(), regData.getLastName(), 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.validate());
    }

    @Test(dependsOnMethods = "verifySuccessRegistration")
    public void verifyRepeatedRegistration() throws IOException {
        MainResponse<ApplicantRegistrationData> response = apiService.registerApplicant(Constants.API_REG_ID,regData.getEmail(),
                regData.getPassword(), regData.getFirstName(), regData.getLastName(), 1).execute().body();
        Assertion.assertApiFailureResponse(response, 998);
    }

    @Test(dependsOnMethods = "verifySuccessRegistration")
    public void verifyRegistrationInAdmin(){
        HttpActions actions = new HttpActions(Constants.HOST);
        actions.doLogin(Constants.ADMIN_EMAIL, Constants.ADMIN_PASSWORD);
        String id = actions.findUserIdByEmail(Constants.APPLICANT_REG_EMAIL);
        Assert.assertFalse(id.isEmpty(), "USER IS NOT FOUND IN ADMIN!");
        System.out.println("userID: " + id);
    }

    @Test(dependsOnMethods = "initialization")
    public void verifyRegistrationWithoutRegId() throws IOException {
        MainResponse<ApplicantRegistrationData> response = apiService.registerApplicant("",regData.getEmail(),
                regData.getPassword(), regData.getFirstName(), regData.getLastName(), 1).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }

    @Test(dataProvider = "negative", dataProviderClass = dataModels.applicant.ApplicantRegistrationData.class, dependsOnMethods = "initialization")
    public void verifyRegistrationFailure(dataModels.applicant.ApplicantRegistrationData data) throws IOException {
        MainResponse<ApplicantRegistrationData> response = apiService.registerApplicant(Constants.API_REG_ID,data.getEmail(),
                data.getPassword(), data.getFirstName(), data.getLastName(), 1).execute().body();
        Assertion.assertApiFailureResponse(response, 998);
    }

    @AfterClass
    @Features({"Delete user by email from admin"})
    public void deleteUserFromAdmin() {
        HttpActions actions = new HttpActions(Constants.HOST);
        actions.deleteUserByEmail(Constants.APPLICANT_REG_EMAIL);
    }
}
