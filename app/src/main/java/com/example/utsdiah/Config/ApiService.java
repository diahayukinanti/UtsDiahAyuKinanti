package com.example.utsdiah.Config;

import com.example.utsdiah.Models.GitHubResponse;
import com.example.utsdiah.Models.ItemUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<GitHubResponse> getUsers(@Query("q") String username);

    @GET("users/{username}")
    Call<ItemUser> getDetailUser(@Path("username") String username);

    String TOKEN = "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";
}
