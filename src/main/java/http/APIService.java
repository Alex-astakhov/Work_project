package http;

import core.Constants;
import dataModels.apiModels.v1.EmptyData;
import dataModels.apiModels.v1.MainResponse;
import dataModels.apiModels.v1.activity.*;
import dataModels.apiModels.v1.company.SearchCompanyData;
import dataModels.apiModels.v1.company.WatchCompanyData;
import dataModels.apiModels.v1.errorModels.*;
import dataModels.apiModels.v1.errorModels.errorBlocks.ErrorDataWithBlocks;
import dataModels.apiModels.v1.errorModels.errorBlocks.LanguageDataParams;
import dataModels.apiModels.v1.errorModels.errorBlocks.SocialNetworksDataParams;
import dataModels.apiModels.v1.listEntities.AllStaticData;
import dataModels.apiModels.v1.listEntities.AutoCompleteTagData;
import dataModels.apiModels.v1.listEntities.GetCitiesData;
import dataModels.apiModels.v1.notification.ListNotificationData;
import dataModels.apiModels.v1.resume.Resume;
import dataModels.apiModels.v1.resume.ResumeData;
import dataModels.apiModels.v1.subscriptions.ListSubscriptionData;
import dataModels.apiModels.v1.subscriptions.ListSubscriptionVacancyData;
import dataModels.apiModels.v1.user.*;
import dataModels.apiModels.v1.vacancy.ListPickedVacanciesData;
import dataModels.apiModels.v1.vacancy.VacancySearchData;
import dataModels.apiModels.v1.vacancy.VacancyWatchData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Asta on 27.04.2017.
 */
public interface APIService {
// ----------------------------------------------- LIST ENTITIES ------------------------------------------------------
    @GET("api/" + Constants.API_VERSION + "/listEntities/getCities")
    Call<MainResponse<GetCitiesData>> getCities();

    @GET("api/" + Constants.API_VERSION + "/listEntities/allStatic")
    Call<MainResponse<AllStaticData>> allStatic();

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/listEntities/AutoCompleteTag")
    Call<MainResponse<AutoCompleteTagData>> autoCompleteTag(@Field("query") String text);
// --------------------------------------------------------------------------------------------------------------------

// ---------------------------------------------------- USER ----------------------------------------------------------
    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/GetLinkAutoLogin")
    Call<MainResponse<GetLinkAutoLoginData>> getLinkAutoLogin(@Field("regId") String regId,
                                                              @Field("access_token") String token,
                                                              @Field("role") int role);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/login")
    Call<MainResponse<ApplicantLoginData>> userLogin(@Field("regId") String regId,
                                                     @Field("Users[email]") String email,
                                                     @Field("Users[password]") String password);


    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/RegisterApplicant")
    Call<MainResponse<ApplicantRegistrationData>> registerApplicant(@Field("regId") String regId,
                                                                    @Field("Users[email]") String email,
                                                                    @Field("Users[password]") String password,
                                                                    @Field("Users[first_name]") String firstName,
                                                                    @Field("Users[last_name]") String lastName,
                                                                    @Field("Users[role]") int role);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/logOut")
    Call<MainResponse<User>> deleteAvatar(@Field("regId") String regId,
                                          @Field("access_token") String token,
                                          @Field("role") int role);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/forgotPassword")
    Call<MainResponse<EmptyData>> userForgotPassword(@Field("Users[email]") String email);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/logOut")
    Call<MainResponse<EmptyData>> userLogout(@Field("regId") String regId,
                                             @Field("access_token") String token,
                                             @Field("role") int role);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/sendSupport")
    Call<MainResponse<EmptyData>> userSendSupport(@Field("SupportForm[text]") String text,
                                                  @Field("regId") String regId,
                                                  @Field("access_token") String token,
                                                  @Field("role") int role);

    @Multipart
    @POST("api/" + Constants.API_VERSION + "/user/uploadAvatar")
    Call<MainResponse<User>> uploadAvatar(@Part MultipartBody.Part file,
                                          @Part("regId") RequestBody regId,
                                          @Part("access_token") RequestBody token,
                                          @Part("role") RequestBody role);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/user/upsertUserAbout")
    Call<MainResponse<UserAboutData<UserAboutErrorData>>> upsertUserAbout(@Field("id") int id,
                                                                          @Field("Users[first_name]") String firstName,
                                                                          @Field("Users[last_name]") String lastName,
                                                                          @Field("Users[middle_name]") String middleName,
                                                                          @Field("Users[birth_day]") String birthDay,
                                                                          @Field("Users[birth_month]") String birthMonth,
                                                                          @Field("Users[birth_year]") String birthYear,
                                                                          @Field("Users[gender]") int gender,
                                                                          @Field("Users[city_id]") int cityId,
                                                                          @Field("UsersInfo[city_region_id]") int cityRegionId,
                                                                          @Field("Users[phone]") String phone,
                                                                          @Field("UsersInfo[additional_phone]") String additionalPhone,
                                                                          @Field("UsersInfo[skype]") String skype,
                                                                          @Field("regId") String regId,
                                                                          @Field("access_token") String token,
                                                                          @Field("role") int role);
// --------------------------------------------------------------------------------------------------------------------

// ---------------------------------------------------- ACTIVITY ------------------------------------------------------
    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/notebookApplicant/ResumeList")
    Call<MainResponse<ResumeListData>> resumeList(@Field("regId") String regId,
                                                  @Field("access_token") String token,
                                                  @Field("role") int role);


// --------------------------------------------------------------------------------------------------------------------

// ---------------------------------------------------- COMPANY -------------------------------------------------------
    @GET("api/" + Constants.API_VERSION + "/company/search")
    Call<MainResponse<SearchCompanyData>> searchCompany(@Query("query") String query, @Query("page") int page);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/company/watch")
    Call<MainResponse<WatchCompanyData>> watchCompany(@Field("id") int id,
                                                      @Field("regId") String regId,
                                                      @Field("access_token") String token,
                                                      @Field("role") int role,
                                                      @Field("page") int page);
// --------------------------------------------------------------------------------------------------------------------

// ------------------------------------------------- NOTIFICATION -----------------------------------------------------
    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/notification/EditNotification")
    Call<MainResponse<EmptyData>> editNotification(@Field("id") int id,
                                              @Field("NotificationUserSetting[is_active]") int isActive,
                                              @Field("regId") String regId,
                                              @Field("access_token") String token,
                                              @Field("role") int role);

    @FormUrlEncoded
    @POST("api/" + Constants.API_VERSION + "/notification/ListNotification")
    Call<MainResponse<ListNotificationData>> listNotification(@Field("regId") String regId,
                                                              @Field("access_token") String token,
                                                              @Field("role") int role);
// --------------------------------------------------------------------------------------------------------------------


// ------------------------------------------------ VACANCY ------------------------------------------------------

    @GET("api/" + Constants.API_VERSION + "/vacancy/search")
    Call<MainResponse<VacancySearchData>> search(@Query("page") int page,
                                                 @Query("query") String query,
                                                 @Query("city_id") int cityId,
                                                 @Query("total_experience[total_experience_from]") int experienceFrom,
                                                 @Query("total_experience[total_experience_to]") int experienceTo,
                                                 @Query("salary[salary_from]") int salaryFrom,
                                                 @Query("salary[salary_to]") int salaryTo,
                                                 @Query("availability_id") int availabilityId,
                                                 @Query("title") int title,
                                                 @Query("all_words") int allWords,
                                                 @Query("regId") String regId,
                                                 @Query("access_token") String token,
                                                 @Query("role") int role);

// --------------------------------------------------------------------------------------------------------------------
}
