package co.apidemos.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by dharma kshetri on 12/27/16.
 */

public class APIClient {

    public static  final  String STACKOVERFLOW_BASE_URL="https://api.stackexchange.com" ;

    public static final String GITHUB_BASE_URL="https://api.github.com";

    public final String BASE_URL = "https://api.twitch.tv/kraken/";


    public static Retrofit retrofit=null;

    public static Retrofit retrofitGitHub=null;

    // get stackoverflow client
    public static  Retrofit getStackOverFLowClient(){
        if(retrofit == null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(STACKOVERFLOW_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }

    //get github client

    public static  Retrofit getGitHubClient(){
        if(retrofitGitHub == null){
            retrofitGitHub=new Retrofit.Builder()
                    .baseUrl(GITHUB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofitGitHub;
    }

    // get twitch client


}
