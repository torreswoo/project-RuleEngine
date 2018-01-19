package com.kakaopay.service.ruleEngine;

import com.kakaopay.service.rule.Rule;

public interface RuleEngine {

    void addRule(Rule rule);
    String execute();
}
