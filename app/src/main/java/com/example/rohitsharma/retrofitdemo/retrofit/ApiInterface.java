package com.example.rohitsharma.retrofitdemo.retrofit;

import com.example.rohitsharma.retrofitdemo.model.country.CountryModel;
import com.example.rohitsharma.retrofitdemo.model.state.StateModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rohit.Sharma on 7/14/2017.
 */

public interface ApiInterface {
    // Request method (like getCountryList) and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("country/get/all")
    Call<CountryModel> getCountryList();

    @GET("country/get/iso2code/{alpha2_code}")
    Call<ResponseBody> getCountyInfo(@Path("alpha2_code") String alphaCode);

    @GET("state/get/{countryCode}/all")
    Call<StateModel> getStateList(@Path("countryCode") String countryCode);

    @GET("state/get/{countryCode}/{stateCode}")
    Call<ResponseBody> getStateInfo(@Path("countryCode") String countryCode, @Path("stateCode") String stateCode);

}
