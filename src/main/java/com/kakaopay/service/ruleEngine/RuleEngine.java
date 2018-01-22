package com.kakaopay.service.ruleEngine;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.rule.Rule;

import java.util.List;
import java.util.Map;

public interface RuleEngine {

    void addRule(Rule rule);
    void execute(List<UserActionLog> userActionLogList) throws Exception;
    String resultFDSfromRuleEngine();
}
