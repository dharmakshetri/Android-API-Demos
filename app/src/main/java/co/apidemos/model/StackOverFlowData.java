package co.apidemos.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by dharma kshetri on 12/27/16.
 */

public class StackOverFlowData {
    @SerializedName("items")
    private List<StackOverFlowUser> users;

    public  void setUsers(List<StackOverFlowUser> users){this.users=users;}
    public List<StackOverFlowUser> getUsers(){ return users;}
}
