package ussrfantom.com.example.androidtesttask.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ussrfantom.com.example.androidtesttask.pojo.Example;

public interface ApiService {
    @GET("data.cgi?code=4431084557&p=1/")
    Observable <Example> getExample();
}
