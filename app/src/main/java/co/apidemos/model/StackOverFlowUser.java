package co.apidemos.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;


/**
 * Created by dharma kshetri on 12/27/16.
 */
public class StackOverFlowUser {
    @SerializedName("location")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public HashMap<String, Integer> getBadges() {
        return badges;
    }

    public void setBadges(HashMap<String, Integer> badges) {
        this.badges = badges;
    }

    @SerializedName("display_name")
    private String userName;

    @SerializedName("reputation")
    private  String reputation;

    @SerializedName("badge_counts")
    private HashMap<String, Integer> badges= new HashMap<>();
}
