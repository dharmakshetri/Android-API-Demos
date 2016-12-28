package co.apidemos.rest;

import co.apidemos.model.StackOverFlowData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dharma kshetri on 12/27/16.
 */

public interface UserEndPoints {

    @GET("/2.2/users?pagesize=10&order=desc&sort=reputation&site=stackoverflow")
    Call<StackOverFlowData> getUsers(@Query("sort") String sort);
}
