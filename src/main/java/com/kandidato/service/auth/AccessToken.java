package com.kandidato.service.auth;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("expires_in")
    private int expiresIn;

    @SerializedName("access_token")
    private String accessToken;

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
