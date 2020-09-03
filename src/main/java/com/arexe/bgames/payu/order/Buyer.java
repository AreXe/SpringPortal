package com.arexe.bgames.payu.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Buyer {

    private String email;
    private String language;

}
