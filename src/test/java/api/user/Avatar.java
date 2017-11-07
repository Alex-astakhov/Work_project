package api.user;

import assertion.Assertion;
import core.Constants;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.user.User;
import http.RetrofitClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.File;
import java.io.IOException;

@Features("API upload and delete avatar")
public class Avatar extends RetrofitClient {
    private RequestBody accessToken;
    private RequestBody regId = RequestBody.create(MediaType.parse("text/plain"), Constants.API_REG_ID);
    private RequestBody role = RequestBody.create(MediaType.parse("text/plain"), "1");

    @Test
    public void login() throws IOException {
        token = UserLoginAndLogout.loginApi(Constants.API_REG_ID, Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD).data.token;
        Assert.assertTrue(token != null, "Token = null!");
        accessToken = RequestBody.create(MediaType.parse("text/plain"), RetrofitClient.token);
    }

    @Test(dependsOnMethods = "login")
    public void verifyUploadFirstAvatar() throws IOException {
        System.out.println("Sending: JPG");
        File image = new File("C:\\UserAvatar\\image1.jpg");
        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("file", image.getName(), propertyImage);
        MainResponse<User> response = apiService.uploadAvatar(propertyImagePart, regId, accessToken, role).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertNotNull(response.data.avatar);
    }

    @Test(dependsOnMethods = "verifyUploadFirstAvatar")
    public void verifyUploadSecondAvatar() throws IOException {
        System.out.println("Sending: PNG");
        File image = new File("C:\\UserAvatar\\image2.png");
        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("file", image.getName(), propertyImage);
        MainResponse<User> response = apiService.uploadAvatar(propertyImagePart, regId, accessToken, role).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertNotNull(response.data.avatar);
    }

    @Test(dependsOnMethods = "verifyUploadSecondAvatar")
    public void verifyDeletingAvatar() throws IOException {
        MainResponse<User> response = apiService.deleteAvatar(Constants.API_REG_ID, token, 1).execute().body();
        Assertion.assertApiSuccessResponse(response);
        Assert.assertNull(response.data.avatar);
    }

    @Test(dependsOnMethods = "verifyDeletingAvatar")
    public void verifyUploadSmallAvatar() throws IOException {
        System.out.println("Sending: small JPG");
        File image = new File("C:\\UserAvatar\\small.jpg");
        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("file", image.getName(), propertyImage);
        MainResponse<User> response = apiService.uploadAvatar(propertyImagePart, regId, accessToken, role).execute().body();
        Assertion.assertApiFailureResponse(response, 998);
    }

    @Test(dependsOnMethods = "verifyDeletingAvatar")
    public void verifyUploadAvatarWithWrongToken() throws IOException {
        RequestBody wrongToken = RequestBody.create(MediaType.parse("text/plain"), RetrofitClient.token + "qwerty");
        File image = new File("C:\\UserAvatar\\image1.jpg");
        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("file", image.getName(), propertyImage);
        MainResponse<User> response = apiService.uploadAvatar(propertyImagePart, regId, wrongToken, role).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }

    @Test(dependsOnMethods = "verifyUploadSecondAvatar")
    public void verifyDeletingAvatarWithWrongToken() throws IOException {
        MainResponse<User> response = apiService.deleteAvatar(Constants.API_REG_ID, token + "qwerty", 1).execute().body();
        Assertion.assertApiFailureResponse(response, 997);
    }
}
