package http;


import core.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by Asta on 10.07.2017.
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;
    protected static APIService apiService;
    protected static String token;

    public static APIService getClient(String baseUrl) {
        if (retrofit==null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(APIService.class);
    }

    @BeforeTest
    @Parameters({"stage"})
    public void setUp(@Optional("true") boolean stage){
        if (stage){
            Constants.DOMEN = "https://rabotadev.nur.kz/";
            Constants.HOST = "rabotadev.nur.kz";
        }
        else {
            Constants.DOMEN = "https://rabota.nur.kz/";
            Constants.HOST = "rabota.nur.kz";
        }
        apiService = getClient(Constants.DOMEN);
    }

    @AfterTest
    public void logout() throws IOException {
        System.out.println("Logout with token: " + token);
        apiService.userLogout(Constants.API_REG_ID, token, 1).execute();
    }
}
