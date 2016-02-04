package com.lexing.learndagger.models;

import com.google.gson.annotations.SerializedName;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 11:11
 */
public class GitUser {
    @SerializedName("login")
    private String nickName;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
