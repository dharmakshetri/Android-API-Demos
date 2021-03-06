package co.apidemos.rest;

import java.util.List;

import co.apidemos.model.github.GitHubData;
import co.apidemos.model.github.GitHubUser;
import co.apidemos.model.instagram.InstagramUser;
import co.apidemos.model.ip.IPData;
import co.apidemos.model.stackoverflow.StackOverFlowData;
import co.apidemos.model.weather.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dharma kshetri on 12/27/16.
 */

public interface UserEndPoints {
    //get the user of stackoverflow
    @GET("/2.2/users?pagesize=10&order=desc&sort=reputation&site=stackoverflow")
    Call<StackOverFlowData> getUsers(@Query("sort") String sort);

    // github
    @GET("/users/{user}")
    Call<GitHubUser> getGitHubUser(@Path("user") String user);


    @GET("/users/{user}/repos")
    Call<List<GitHubData>> getRepositories(@Path("user") String name);


    //twitch
   // @GET("games/top")
    //Call<Twitch> getTopGames(@Header("Client-Id") String clientId);

    //instagram
    @GET("/v1/users/self/")
    Call<InstagramUser> getInstagramUser(@Query("access_token") String authtoken);

    //ip
    @GET("/json")
    Call<IPData> getIpDetails();

    //weather
    @GET("weather")
    //Call<WeatherData> getWeatherData(@Query("q") String city);
    Call<Weather> getWeatherData(@Query("q") String q, @Query("appid") String appid);

}
