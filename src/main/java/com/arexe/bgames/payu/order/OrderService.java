package com.arexe.bgames.payu.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Value("${payu.order.url}")
    private String payuOrderUrl;

    @Resource(name = "payuRestTemplate")
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public OrderResponse createOrder(final OrderRequest orderCreateRequest) {
        final ResponseEntity<String> jsonResponse = restTemplate.postForEntity(payuOrderUrl, orderCreateRequest, String.class);
        return objectMapper.readValue(jsonResponse.getBody(), OrderResponse.class);
    }

}
