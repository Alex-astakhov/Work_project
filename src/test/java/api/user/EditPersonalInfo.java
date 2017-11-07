package api.user;

import assertion.Assertion;
import core.Constants;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.activity.ResumeListData;
import dataModels.apiModels.v1.errorModels.UserAboutErrorData;

import dataModels.apiModels.v1.user.UserAboutData;
import dataModels.applicant.cvParts.UserData;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

@Features("API edit personal info in resume")
public class EditPersonalInfo extends RetrofitClient {
    private UserData userData = new UserData();

    @Test
    public void login() throws IOException {
        token = UserLoginAndLogout.loginApi(Constants.API_REG_ID, Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD).data.token;
        Assert.assertTrue(token != null, "Token = null!");
        MainResponse<ResumeListData> response = apiService.resumeList(Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
    }

    @Test(dependsOnMethods = "login")
    public void verifyChangePersonalInfo() throws IOException {
        System.out.println("Edit data via resume with ID = " +  Constants.API_RESUME_ID_DEV_FOR_EDIT);
        int region = 0;
        if (userData.getCityRegionValue() != null)
            region = Integer.parseInt(userData.getCityRegionValue());
        MainResponse<UserAboutData<UserAboutErrorData>> response = apiService.upsertUserAbout( Constants.API_RESUME_ID_DEV_FOR_EDIT,
                userData.getFirstName(), userData.getLastName(), userData.getMiddleName(),
                String.valueOf(userData.getBirthDayIndex()), String.valueOf(userData.getBirthMonthIndex()),
                userData.getBirthYearValue(), userData.getGenderAsInt(), Integer.parseInt(userData.getCityValue()),
                region, userData.getPhoneNumber(),
                userData.getAdditionPhoneNumber(), userData.getSkype(), Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
        UserData actualUserData = response.data.getUserData();
        System.out.println("Actual userData:   " + actualUserData);
        System.out.println("Expected userData: " + userData);
        Assert.assertEquals(actualUserData, userData, "User data from JSON is not equals to expected!");
    }

    @Test(dependsOnMethods = "verifyChangePersonalInfo", dataProvider = "negativeForApi", dataProviderClass = UserData.class)
    public void verifyInvalidSubmits(UserData userData, UserAboutErrorData errorData) throws IOException {
        int region = 0;
        if (userData.getCityRegionValue() != null)
            region = Integer.parseInt(userData.getCityRegionValue());
        System.out.println("Edit data via resume with ID = " +  Constants.API_RESUME_ID_DEV_FOR_EDIT);
        System.out.println("Submitting data:\n" + userData);
        MainResponse<UserAboutData<UserAboutErrorData>> response = apiService.upsertUserAbout( Constants.API_RESUME_ID_DEV_FOR_EDIT,
                userData.getFirstName(), userData.getLastName(), userData.getMiddleName(),
                String.valueOf(userData.getBirthDayIndex()), String.valueOf(userData.getBirthMonthIndex()),
                userData.getBirthYearValue(), userData.getGenderAsInt(), Integer.parseInt(userData.getCityValue()),
                region, userData.getPhoneNumber(), userData.getAdditionPhoneNumber(),
                userData.getSkype(), Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiFailureResponse(response, 998);
        Assert.assertEquals(response.data.errors, errorData);
    }

    @Test(dependsOnMethods = "verifyChangePersonalInfo")
    public void verifyChangePersonalInfoWithWrongToken() throws IOException {
        System.out.println("Edit data via resume with ID = " +  Constants.API_RESUME_ID_DEV_FOR_EDIT);
        int region = 0;
        if (userData.getCityRegionValue() != null)
            region = Integer.parseInt(userData.getCityRegionValue());
        MainResponse<UserAboutData<UserAboutErrorData>> response = apiService.upsertUserAbout(Constants.API_RESUME_ID_DEV_FOR_EDIT,
                userData.getFirstName(), userData.getLastName(), userData.getMiddleName(),
                String.valueOf(userData.getBirthDayIndex()), String.valueOf(userData.getBirthMonthIndex()),
                userData.getBirthYearValue(), userData.getGenderAsInt(), Integer.parseInt(userData.getCityValue()),
                region, userData.getPhoneNumber(), userData.getAdditionPhoneNumber(), userData.getSkype(),
                Constants.API_REG_ID, token + "qwerty", 1).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }
}
