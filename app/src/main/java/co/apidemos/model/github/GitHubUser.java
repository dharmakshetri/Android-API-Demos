package co.apidemos.model.github;

import com.google.gson.annotations.SerializedName;

/**
 * Created by horror on 12/29/16.
 */

public class GitHubUser {
    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public GitHubUser(String login, String name, String email, String followers, String following, String avatarUrl) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.followers = followers;
        this.following = following;
        this.avatarUrl = avatarUrl;
    }


    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }





}
