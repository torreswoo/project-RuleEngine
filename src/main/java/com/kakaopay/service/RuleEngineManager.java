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

    public String start(List<UserActionLog> userActionLogList, Date requestTime){
        // setup Runtime Rules
        this.ruleConfig.setUpRuntimeRules(requestTime);

        // execute Rule Engine
        this.kakaoRuleEngine.execute(userActionLogList);
        return this.kakaoRuleEngine.resultFDSfromRuleEngine();

    }

//    public void setUpBasicRules(){
//
//        // RuleA
//        Rule ruleA = new KakaoMoneyRule("RuleA");
//
//        Condition ruleACondition01 = new OpenAccountInTimeCondition(1);
//        Condition ruleACondition02 = new ChargingMoneyCondition(2000000);
//        CheckCondition ruleACheckCondition = new CheckBalance(1000);
//
//        ruleA.addCondition(ruleACondition01);
//        ruleA.addCondition(ruleACondition02);
//        ruleA.addCheckCondition(ruleACheckCondition.getCheckCondition());
//        this.kakaoRuleEngine.addRule(ruleA);
//
//        // RuleB
//        Rule ruleB = new KakaoMoneyRule("RuleB");
//
//        Condition ruleBCondition01 = new OpenAccountInTimeCondition(7*24);
//        Condition ruleBCondition02 = new ReceiveMoneyCondition(100000);
//        CheckCondition ruleBCheckCondition = new CheckNumber(5);
//
//        ruleB.addCondition(ruleBCondition01);
//        ruleB.addCondition(ruleBCondition02);
//        ruleB.addCheckCondition(ruleBCheckCondition.getCheckCondition());
//        this.kakaoRuleEngine.addRule(ruleB);
//
//        log.info("[RULE ENGINE] set up basic rules...");
//
//    }
//
//    public void setUpRuntimeRules(Date requestTime){
//
//        // RuleC
//        Rule ruleC = new KakaoMoneyRule("RuleC");
//
//        Condition ruleCCondition01 = new RequesDateInTimeCondition(2, requestTime);
//        Condition ruleCCondition02 = new ReceiveMoneyCondition(50000);
//        CheckCondition ruleCCheckCondition = new CheckNumber(3);
//
//        ruleC.addCondition(ruleCCondition01);
//        ruleC.addCondition(ruleCCondition02);
//        ruleC.addCheckCondition(ruleCCheckCondition.getCheckCondition());
//        this.kakaoRuleEngine.addRule(ruleC);
//
//        log.info("[RULE ENGINE] set up runtime rules...");
//
//    }
}
