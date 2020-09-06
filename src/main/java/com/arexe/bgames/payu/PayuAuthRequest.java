package com.arexe.bgames.payu;

public class PayuAuthRequest {

    private String authUrl;
    private String clientId;
    private String clientSecret;
    private String grantType;

    public PayuAuthRequest authUrl(final String authUrl) {
        this.authUrl = authUrl;
        return this;
    }

    public PayuAuthRequest clientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }

    public PayuAuthRequest clientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public PayuAuthRequest grantType(final String grantType) {
        this.grantType = grantType;
        return this;
    }

    public String build() {
        return authUrl +
                "?grant_type=" + grantType +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret;
    }

}
