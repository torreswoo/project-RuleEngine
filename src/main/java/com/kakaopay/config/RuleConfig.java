package com.kakaopay.config;

import com.kakaopay.service.RuleEngineManager;
import com.kakaopay.service.condition.*;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import com.kakaopay.service.ruleEngine.RuleEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@Slf4j
public class RuleConfig {

    @Autowired
    private RuleEngineManager ruleEngineManager;

    public void setUpBasicRules(){

        // RuleA
        Rule ruleA = new KakaoMoneyRule("RuleA");

        Condition ruleACondition01 = new OpenAccountInTimeCondition(1);
        Condition ruleACondition02 = new ChargingMoneyCondition(2000000);
        CheckCondition ruleACheckCondition = new CheckBalance(1000);

        ruleA.addCondition(ruleACondition01);
        ruleA.addCondition(ruleACondition02);
        ruleA.addCheckCondition(ruleACheckCondition.getCheckCondition());
        this.ruleEngineManager.addRule(ruleA);

        // RuleB
        Rule ruleB = new KakaoMoneyRule("RuleB");

        Condition ruleBCondition01 = new OpenAccountInTimeCondition(7*24);
        Condition ruleBCondition02 = new ReceiveMoneyCondition(100000);
        CheckCondition ruleBCheckCondition = new CheckNumber(5);

        ruleB.addCondition(ruleBCondition01);
        ruleB.addCondition(ruleBCondition02);
        ruleB.addCheckCondition(ruleBCheckCondition.getCheckCondition());
        this.ruleEngineManager.addRule(ruleB);

        log.info("[RULE ENGINE] set up basic rules...");

    }

    public void setUpRuntimeRules(Date requestTime){

        // RuleC
        Rule ruleC = new KakaoMoneyRule("RuleC");

        Condition ruleCCondition01 = new RequesDateInTimeCondition(2, requestTime);
        Condition ruleCCondition02 = new ReceiveMoneyCondition(50000);
        CheckCondition ruleCCheckCondition = new CheckNumber(3);

        ruleC.addCondition(ruleCCondition01);
        ruleC.addCondition(ruleCCondition02);
        ruleC.addCheckCondition(ruleCCheckCondition.getCheckCondition());
        this.ruleEngineManager.addRule(ruleC);

        log.info("[RULE ENGINE] set up runtime rules...");

    }
}
