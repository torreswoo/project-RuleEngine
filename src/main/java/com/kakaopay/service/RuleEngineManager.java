package com.kakaopay.service;

import com.kakaopay.config.RuleConfig;
import com.kakaopay.entity.MoneyReceivingLog;
import com.kakaopay.entity.TransferLog;
import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.*;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import com.kakaopay.service.ruleEngine.KakaoMoneyRuleEngine;
import com.kakaopay.service.ruleEngine.RuleEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
public class RuleEngineManager {

    @Autowired
    private RuleConfig ruleConfig;

    private RuleEngine kakaoRuleEngine;

    public RuleEngineManager(){
        this.kakaoRuleEngine = new KakaoMoneyRuleEngine();
    }


    public void addRule(Rule rule){
        this.kakaoRuleEngine.addRule(rule);
    }

    public String start(List<UserActionLog> userActionLogList, Date requestTime) throws Exception{
        // setup Runtime Rules
        this.ruleConfig.setUpRuntimeRules(requestTime);

        // execute Rule Engine
        this.kakaoRuleEngine.execute(userActionLogList);
        return this.kakaoRuleEngine.resultFDSfromRuleEngine();

    }
}
