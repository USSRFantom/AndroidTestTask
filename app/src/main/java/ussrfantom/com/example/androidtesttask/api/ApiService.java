package ussrfantom.com.example.androidtesttask.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

import retrofit2.http.Query;
import ussrfantom.com.example.androidtesttask.pojo.Example;

public interface ApiService {
    @GET("data.cgi")
    Observable <Example> getExample(
            @Query("code") String code,
            @Query("p") String page);


}

/*
public interface ApiService {
    @GET("data.cgi")
    Observable <Example> getExample(
            @Query("code") String code,
            @Query("p") String page);


}
 */
