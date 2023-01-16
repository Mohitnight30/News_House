package com.example.newshouse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetofitInstance {
    public static RetofitInstance instance;
    apiInterface apiInterface;
    String URI="https://newsapi.org/";

    RetofitInstance(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URI)
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiInterface=retrofit.create(apiInterface.class);
    }

    public static RetofitInstance getIntance(){
        if(instance==null){
            instance=new RetofitInstance();
        }
        return instance;

    }
}
