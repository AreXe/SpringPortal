package com.arexe.bgames.config;

import com.arexe.bgames.payu.PayuAuthResponse;
import com.arexe.bgames.payu.PayuAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class PayuConfig {

    private final PayuAuthService payuAuthService;

    @Autowired
    public PayuConfig(PayuAuthService payuAuthService) {
        this.payuAuthService = payuAuthService;
    }

    @Bean("payuRestTemplate")
    public RestTemplate payuRestTemplate() {
        final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList((httpRequest, bytes, clientHttpRequestExecution) -> {
            final PayuAuthResponse authResponse = payuAuthService.authenticate();
            final HttpHeaders headers = httpRequest.getHeaders();
            headers.add("Authorization", authResponse.getTokenType() + " " + authResponse.getAccessToken());
            if (!headers.containsKey("Content-Type")) {
                headers.add("Content-Type", "application/json");
            }
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }));
        return restTemplate;
    }

}
