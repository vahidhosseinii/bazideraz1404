package com.shohda.bazideraz.Api;

import java.util.List;

import com.shohda.bazideraz.ServerData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("verifylogin")
    Call<ServerData.verfiy> verfiy(@Field("phone") String mobile, @Field("verificationCode") String password);


}