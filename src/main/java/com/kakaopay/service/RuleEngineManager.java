package com.kakaopay.service;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;
import com.kakaopay.service.condition.OpenAccountInTimeCondition;
import com.kakaopay.service.condition.ReceiveMoneyCondition;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import com.kakaopay.service.ruleEngine.KakaoMoneyRuleEngine;
import com.kakaopay.service.ruleEngine.RuleEngine;

import java.util.List;

public class RuleEngineManager {

    public RuleEngineManager(){

    }
    public String start(List<UserActionLog> userActionLogList){

        //RuleB
        Condition ruleBCondition01 = new OpenAccountInTimeCondition(7*24);
        Condition ruleBCondition02 = new ReceiveMoneyCondition(100000);
        Rule ruleB = new KakaoMoneyRule("RuleB");
        ruleB.addCondition(ruleBCondition01);
        ruleB.addCondition(ruleBCondition02);
        ruleB.addCheckCondition((List<UserActionLog> userActionLogs) -> userActionLogs.size() >= 5);

        // Rule Engine
        RuleEngine kakaoRuleEngine = new KakaoMoneyRuleEngine();
        kakaoRuleEngine.addRule(ruleB);
        kakaoRuleEngine.execute(userActionLogList);
        return kakaoRuleEngine.resultFDSfromRuleEngine();

    }
}
