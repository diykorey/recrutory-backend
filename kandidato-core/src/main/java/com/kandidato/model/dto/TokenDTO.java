package com.kandidato.model.dto;

import javax.validation.constraints.NotNull;

public class TokenDTO {

    @NotNull
    private String provider;

    @NotNull
    private String token;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
