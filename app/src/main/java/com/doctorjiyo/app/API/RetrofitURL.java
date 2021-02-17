package com.doctorjiyo.app.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitURL {


    private static Retrofit retrofit=null;

    public static Retrofit getClient(){

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit=builder.build();

        return retrofit;
    }

}
