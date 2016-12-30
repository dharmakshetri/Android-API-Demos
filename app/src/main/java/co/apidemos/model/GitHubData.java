package co.apidemos.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by horror on 12/29/16.
 */

public class GitHubData {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    public GitHubData(String name, String description, String language) {
        this.name = name;
        this.description = description;
        this.language = language;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }








}
