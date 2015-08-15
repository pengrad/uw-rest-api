# uw-rest-api


    @FormUrlEncoded
    @POST("/auth/login")
    void login(@Field("email") String email, @Field("password") String password, Callback<JsonElement> callback);

    @GET("/user")
    void user(@Header("Authorization") String token, Callback<JsonElement> callback);

    @GET("/products")
    void products(@Header("Authorization") String token, Callback<JsonElement> callback);

    @GET("/products/{id}")
    void product(@Header("Authorization") String token, @Path("id") int productId, Callback<JsonElement> callback);
