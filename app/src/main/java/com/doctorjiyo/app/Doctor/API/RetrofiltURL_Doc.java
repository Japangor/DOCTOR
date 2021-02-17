package com.doctorjiyo.app.Doctor.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofiltURL_Doc {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://doctoron.in/api/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit=builder.build();
        return retrofit;
    }
}
