package http;

import core.Constants;
import okhttp3.*;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asta on 18.01.2017.
 */
public class HttpActions {
    private HttpUrl url;
    private String host;
    private final String SCHEME = "https";
    private CookieJar cookieStore = new CookieStorage();

    public HttpActions(String host) {
        this.host = host;
    }

    @Step("Send GET request: {0}")
    public String sendGetRequestToEndpoint(String endpoint){
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments(endpoint).build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return "";
    }

    @Step("HTTP: login with email: {0} and password: {1}")
    public Response doLogin(String email, String password) {
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegment("login").build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder().add("Users[email]", email).add("Users[password]", password).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return response;
    }

    @Step("HTTP: find user by email: {0}")
    public String findUserIdByEmail(String email) {
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("admin/user").build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder().add("email", email).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.contains("Ничего не найдено"))
            return "";
        result = result.substring(result.indexOf("Email/id/") + 9);
        result = result.substring(0, result.indexOf("\">"));
        response.body().close();
        return result;
    }

    @Step("HTTP: verify {0} is confirmed")
    public boolean isEmailConfirmed(String email) {
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("admin/user").build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder().add("email", email).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return !result.contains("Не подтвержден");
    }

    @Step("HTTP: change user's email by user ID: {0} and new email: {1}")
    public boolean changeUserEmail(String userId, String newEmail){
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("admin/user/changeEmail/id/" + userId).build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder().add("email", newEmail).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return result.contains("Email изменен!");
    }

    @Step("HTTP: change user's email by user ID: {0} and new email: {1}")
    public boolean createManagerForBoss(String managerEmail, String managerFirstName, String managerLastName){
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("employer/createManager").build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder()
                .add("Users[email]", managerEmail)
                .add("Users[first_name]", managerFirstName)
                .add("Users[last_name]", managerLastName).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return result.contains(managerEmail);
    }

    @Step("HTTP: change user's email by user ID: {0} and new email: {1}")
    public boolean sendInviteOnResume(String managerEmail, String managerFirstName, String managerLastName){
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("employer/createManager").build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder()
                .add("Users[email]", managerEmail)
                .add("Users[first_name]", managerFirstName)
                .add("Users[last_name]", managerLastName).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return result.contains(managerEmail);
    }

    @Step("HTTP: change user's password by user ID: {0} and new password: {1}")
    public boolean changeUserPassword(String userId, String newPassword){
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("admin/user/changePassword/id/" + userId).build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        RequestBody body = new FormBody.Builder().add("password", newPassword).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return result.contains("Пароль изменен!");
    }

    @Step("HTTP: delete user by Id: {0}")
    public String deleteUserById(String userId) {
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("admin/user/delete/id/" + userId).build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return "";
    }

    @Step("Delete user by email: {0}")
    public void deleteUserByEmail(String email) {
        HttpActions actions = new HttpActions(Constants.HOST);
        actions.doLogin(Constants.ADMIN_EMAIL, Constants.ADMIN_PASSWORD);
        String id = actions.findUserIdByEmail(email);
        if (id.isEmpty()){
            System.out.println("User with email <" + email + "> is not found");
            return;
        }
        System.out.println("User id to delete: " + id);
        String actualResponse = actions.deleteUserById(id);
        if(actualResponse.contains("удален!"))
            System.out.println("User with email <" + email + "> was deleted successfully!\n");
        else
            System.out.println("response doesn't contain \"удален!\"");
    }

    private class CookieStorage implements CookieJar {
        List<Cookie> cookies = new ArrayList<>();

        @Override
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            cookies.addAll(list);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            return cookies;
        }
    }

    @Step("HHTP: find vacancy by employer's ID: {0}")
    public String findVacancyByEmployerId(String id) {
        url = new HttpUrl.Builder().scheme(SCHEME).host(host).addPathSegments("admin/company/view/id/" + id).build();
        System.out.println("Sending request to URL: " + url);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(cookieStore).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();
        return result;
    }
}
