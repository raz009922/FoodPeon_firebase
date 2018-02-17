package com.example.dc_raz.foodpeon_again.API;

import com.example.dc_raz.foodpeon_again.Model.Kitchens;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RAZ on 06-Feb-18.
 */

public interface RetroFIT {
    //Api call can be divide in two parts.. one is base url...and other is the name of the desired API.
    //https://simplifiedcoding.net/demos/marvel/ in this.. https://simplifiedcoding.net/demos/ is the base url
    //and /marvel is the name of the api
    // now we will call this marvel from the base urls... here goes the code...

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    // now we define the type of the array as it is json type.. we use LIst
    //we also need to define the type of the list ..hence we have here the movie name.. and for that we also
    // create a java object call kitchen.. so we can use this kitchen as the type of the LIST...


    public Call<List<Kitchens>> getMovies();


}
