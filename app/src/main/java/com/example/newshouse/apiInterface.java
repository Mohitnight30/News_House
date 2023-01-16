package com.example.newshouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiInterface {
    @GET("v2/top-headlines?country=in&apiKey=9a03c9d54c244dbc8048ab09f0e8d720")
    Call<JSONRespond> getNewsData();
}
