package com.polylink.usermicroservice.payload.response;


import com.polylink.usermicroservice.dto.ResponseUser;


public class JwtRegisterResponse {
    private String token;
    private ResponseUser user;
    private String type = "Bearer";


    public JwtRegisterResponse(String accessToken, ResponseUser user) {
        this.token = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public ResponseUser getUser() {
        return user;
    }

    public void setUser(ResponseUser user) {
        this.user = user;
    }
}


