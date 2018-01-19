package com.kakaopay.service.ruleEngine;

import com.kakaopay.service.rule.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KakaoMoneyRuleEngine implements RuleEngine {

    private Map<String, Rule> ruleMap = new HashMap<String, Rule>();

    public KakaoMoneyRuleEngine(){
    }

    @Override
    public void addRule(Rule rule) {
        ruleMap.put(rule.getRuleName(), rule);
    }

    @Override
    public String execute() {
        // TODO: concurrency & applyFilter conditions in each rules

        return "RuleA,RuleB";
    }
}
