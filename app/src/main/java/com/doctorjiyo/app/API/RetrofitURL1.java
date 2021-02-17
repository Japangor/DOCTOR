package com.doctorjiyo.app.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitURL1 {

  public static String DoctorID="1";
  public static String AttendentID="1";

  private static Retrofit retrofit=null;

  public static Retrofit getClient(){

    Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl("http://manage.doctorjiyo.com/api/")
            .addConverterFactory(GsonConverterFactory.create());

    retrofit=builder.build();

    return retrofit;
  }

}