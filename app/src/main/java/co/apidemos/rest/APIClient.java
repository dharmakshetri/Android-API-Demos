package co.apidemos.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by dharma kshetri on 12/27/16.
 */

public class APIClient {

    public static  final  String STACKOVERFLOW_BASE_URL="https://api.stackexchange.com" ;

    public static final String GITHUB_BASE_URL="https://api.github.com";

    public static final String INSTAGRAM_BASE_URL="https://api.instagram.com";
    // create via https://api.instagram.com by passing client id
    public static final String INSTAGRAM_AUTH_TOKEN="1617125211.ec895f1.d0d214a15e234c38a30e2c35cbab9968";


    public static Retrofit retrofit=null;

    public static Retrofit retrofitGitHub=null;

    public static Retrofit retrofitInstragram=null;

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

    // get instagram client
    public static  Retrofit getInstagramClient(){
        if(retrofitInstragram == null){
            retrofitInstragram=new Retrofit.Builder()
                    .baseUrl(INSTAGRAM_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofitInstragram;
    }


}
