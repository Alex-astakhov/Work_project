package http;

/**
 * Created by Asta on 11.07.2017.
 */
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.yandex.qatools.allure.annotations.Attachment;

public class CustomResponse<T> {

    private Response<ResponseBody> response;
    private Class<T> type;
    private final static String EMPTY = "empty";
    private String body = EMPTY;

    public CustomResponse(Response<ResponseBody> response, Class<T> type) {
        this.response = response;
        this.type = type;
    }

    public String getBody() throws IOException {
        if (body.equals(EMPTY)) {
            if (response.isSuccessful())
                body = response.body().string();
            else
                body = response.errorBody().string();
        }
        return body;
    }

    public T getAsObject() {
        try {
            return new Gson().fromJson(getBody(), type);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public String getAsJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(new JsonParser().parse(getBody()));
        } catch (IOException e) {

        }
        return EMPTY;
    }

    public boolean isSuccessful() {
        return response.isSuccessful();
    }

    public int getResponseCode() {
        return response.code();
    }

    public boolean isJson() throws IOException {
        getBody();
        JsonParser parser = new JsonParser();
        try {
            parser.parse(body).getAsJsonObject();
            return true;
        } catch (IllegalStateException e) {
        }
        try {
            parser.parse(body).getAsJsonArray();
            return true;
        } catch (IllegalStateException e) {
        }
        return false;
    }

    @Attachment(value = "{0}")
    public String attachResponse(String name) {
        return this.getAsJson();
    }

    public void assertThatIsJson() {
        try {
            assertTrue(isJson(), "Response body is not a valid json, body: " + getBody());
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public void assertThatIsSuccess() {
        try {
            assertTrue(isSuccessful(), "Response is not success, message: " + getBody());
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public void assertThatResponseCodeIs(int code) throws IOException {
        assertEquals(getResponseCode(), code, "Response code is not correct");
    }
}