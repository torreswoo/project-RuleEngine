package com.kakaopay.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FraudDetectResponse implements Serializable {

    Long user_id;
    Boolean is_fraud;
    String rule;
}
