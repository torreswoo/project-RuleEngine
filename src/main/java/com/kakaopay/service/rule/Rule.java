package com.kakaopay.service.rule;

public interface Rule {

    void addCondition(Condition condition);

    String getRuleName();
}
