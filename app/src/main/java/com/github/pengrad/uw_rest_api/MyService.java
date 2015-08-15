package com.github.pengrad.uw_rest_api;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * stas
 * 8/16/15
 */
public interface MyService {

    @FormUrlEncoded
    @POST("/auth/login")
    void login(@Field("email") String email, @Field("password") String password, Callback<JsonElement> callback);
//    RESPONSE {token}

    @GET("/user")
    void user(@Header("Authorization") String token, Callback<JsonElement> callback);
//    RESPONSE {email, firstname, surname}

    @GET("/products")
    void products(@Header("Authorization") String token, Callback<JsonElement> callback);
//    RESPONSE [{id, title, images[], bsr, rank_change...}, ..]

    @GET("/products/{id}")
    void product(@Header("Authorization") String token, @Path("id") int productId, Callback<JsonElement> callback);
//    { id, title, images, bsr, keywords[{keyword, rank, rank_change, advert_rank, advert_rank_change]...}

}
