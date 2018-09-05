package com.app.gymbuztrainer.retrofit;


import com.app.gymbuztrainer.entities.AllRequestSupportEnt;
import com.app.gymbuztrainer.entities.CmsEnt;
import com.app.gymbuztrainer.entities.Machine;
import com.app.gymbuztrainer.entities.NotificationEnt;
import com.app.gymbuztrainer.entities.ResponseWrapper;
import com.app.gymbuztrainer.entities.User;
import com.app.gymbuztrainer.entities.UserEnt;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface WebService {

    @FormUrlEncoded
    @POST("User/ValidateUser")
    Call<ResponseWrapper<UserEnt>> login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("deviceID") String deviceID,
            @Field("deviceType") int deviceType
    );


    @Multipart
    @POST("User/EditUserProfile")
    Call<ResponseWrapper<UserEnt>> editProfile(
            @Part("FullName") RequestBody FullName,
            @Part("Gender") RequestBody Gender,
            @Part("DOB") RequestBody DOB,
            @Part("Height") RequestBody Height,
            @Part("Weight") RequestBody Weight,
            @Part("About") RequestBody About,
            @Part MultipartBody.Part file
    );

    @FormUrlEncoded
    @POST("User/ToggleNotification")
    Call<ResponseWrapper> ToggleNotification(
            @Field("Value") int Value
    );

    @FormUrlEncoded
    @POST("updateDeviceToken")
    Call<ResponseWrapper> updateToken(
            @Field("device_token") String device_token,
            @Field("device_type") String device_type
    );


    @GET("User/ChangePassword")
    Call<ResponseWrapper> changePassword(
            @Query("oldpassword") String oldpassword,
            @Query("newpassword") String newpassword
    );

    @GET("User/GetUserNotification")
    Call<ResponseWrapper<ArrayList<NotificationEnt>>> getNotifications();

    @GET("Machine/GetAllRequestSupport")
    Call<ResponseWrapper<ArrayList<AllRequestSupportEnt>>> GetAllRequestSupport();

    @GET("Machine/GetAllSupportLog")
    Call<ResponseWrapper<ArrayList<AllRequestSupportEnt>>> GetAllSupportLog(
            @Query("IsCompleted") int IsCompleted
    );

    @FormUrlEncoded
    @POST("Machine/AcceptRequestSupport")
    Call<ResponseWrapper> AcceptRequest(
            @Field("requestSupportID") int requestSupportID
    );

    @GET("Machine/MarkJobDone")
    Call<ResponseWrapper> markJobDone(
            @Query("RequestSupportID") int RequestSupportID
    );

    @GET("General/GetCMS")
    Call<ResponseWrapper<CmsEnt>> getCMS(
            @Query("GymID") int GymID
    );

    @GET("api/User/Logout")
    Call<ResponseWrapper> logout(
            @Query("DeviceType") int DeviceType
    );




}