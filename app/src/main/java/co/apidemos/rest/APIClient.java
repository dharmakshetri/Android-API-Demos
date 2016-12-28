package co.apidemos.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by dharma kshetri on 12/27/16.
 */

public class APIClient {

    public static  final  String STACKOVERFLOW_BASE_URL="https://api.stackexchange.com" ;

    public static Retrofit retrofit=null;

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
}
