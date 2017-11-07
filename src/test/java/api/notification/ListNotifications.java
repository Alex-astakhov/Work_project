package api.notification;

import api.user.UserLoginAndLogout;
import assertion.Assertion;
import core.Constants;
import dataModels.apiModels.v1.EmptyData;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.notification.ListNotificationData;
import http.RetrofitClient;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Features("API list notifications")
public class ListNotifications extends RetrofitClient {
    private List<ListNotificationData.NotificationUser> listNotification;

    @Test
    public void login(){
        token = UserLoginAndLogout.loginApi(Constants.API_REG_ID, Constants.APPLICANT_API_TEST_EMAIL, Constants.PASSWORD).data.token;
        Assert.assertTrue(token != null, "Token = null!");
    }

    @Test(dependsOnMethods = "login")
    public void verifyListNotification() throws IOException {
        MainResponse<ListNotificationData> response = apiService.listNotification(Constants.API_REG_ID,
                token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertTrue(response.validate(), "response is not valid!");
        listNotification = response.data.listNotificationUser;
    }

    @Test(dependsOnMethods = "verifyListNotification")
    public void verifyEditNotification() throws IOException {
        Random random = new Random();
        int index = random.nextInt(listNotification.size());
        int id = listNotification.get(index).id;
        int currentValue = listNotification.get(index).isActive;
        System.out.println("Current value: " + currentValue);
        int newExpectedValue = Math.abs(currentValue - 1);
        System.out.println("Editing notification with id = " + id + ", sending isActive = " + newExpectedValue);
        MainResponse<EmptyData> response = apiService.editNotification(id, newExpectedValue, Constants.API_REG_ID, token, 1)
                .execute().body();
        Assertion.assertApiSuccessResponse(response);
        MainResponse<ListNotificationData> listResponse = apiService.listNotification(Constants.API_REG_ID,
                token, 1).execute().body();
        Assertion.assertApiSuccessResponse(listResponse);
        int actualValue = listResponse.data.listNotificationUser.get(index).isActive;
        Assert.assertEquals(actualValue, newExpectedValue, "isActive value is different!");
    }
}
