package com.arexe.bgames.payu.order;

import lombok.Data;

@Data
public class OrderResponse {

    private String orderId;
    private String redirectUri;
    private Status status;

    @Data
    public static class Status {

        public static final String STATUS_CODE_SUCCESS = "SUCCESS";

        private String statusCode;
    }
}
