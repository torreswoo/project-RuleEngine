package com.torres.domain;

import lombok.Getter;


public class FraudDetectResponse {

    @Getter
    private Long user_id;
    @Getter
    private Boolean is_fraud;
    @Getter
    private String rule;

    // constructor
    public FraudDetectResponse(Long user_id, Boolean is_fraud, String rule){
        this.user_id = user_id;
        this.is_fraud = is_fraud;
        this.rule = rule;
    }

}
