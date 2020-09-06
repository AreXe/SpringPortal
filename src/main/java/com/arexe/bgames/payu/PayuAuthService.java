package com.arexe.bgames.payu;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PayuAuthService {

    public static final String CLIENT_CREDENTIALS = "client_credentials";

    @Value("${payu.auth.url}")
    private String authUrl;

    @Value("${payu.clientId}")
    private String clientId;

    @Value("${payu.clientSecret}")
    private String clientSecret;

    private RestTemplate restTemplate = new RestTemplate();

    @SneakyThrows
    public PayuAuthResponse authenticate() {
        final String authRequest = new PayuAuthRequest()
                .authUrl(authUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(CLIENT_CREDENTIALS)
                .build();

        final ResponseEntity<String> jsonResponse = restTemplate.postForEntity(authRequest, null, String.class);
        return new ObjectMapper().readValue(jsonResponse.getBody(), PayuAuthResponse.class);
    }

}
