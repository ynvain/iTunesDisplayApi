package com.example.ivan.itunesdisplayapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {

        public static JsonApi getInstance() {

            String baseUrl = "https://itunes.apple.com/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(JsonApi.class);
        }
}
