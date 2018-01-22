package com.kakaopay.config;

import com.kakaopay.service.RuleEngineManager;
import com.kakaopay.service.condition.*;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@Slf4j
public class RuleConfig {

    @Autowired
    private RuleEngineManager ruleEngineManager;

    private Rule ruleA;
    private Rule ruleB;
    private Rule ruleC;

    public RuleConfig(
        @Value("${rule.ruleA.name}") String ruleAname,
        @Value("${rule.ruleB.name}") String ruleBname,
        @Value("${rule.ruleC.name}") String ruleCname
    ){
        this.ruleA = new KakaoMoneyRule(ruleAname);
        this.ruleB = new KakaoMoneyRule(ruleBname);
        this.ruleC = new KakaoMoneyRule(ruleCname);
    }

    public void setUpBasicRules(){
        log.info("[RULE ENGINE] set up basic rules...");

        // RuleA
        Condition ruleACondition01 = new OpenAccountInTimeCondition(1);
        Condition ruleACondition02 = new ChargingMoneyCondition(2000000);
        CheckCondition ruleACheckCondition = new CheckBalance(1000);

        ruleA.addCondition(ruleACondition01);
        ruleA.addCondition(ruleACondition02);
        ruleA.addCheckCondition(ruleACheckCondition.getCheckCondition());
        this.ruleEngineManager.addRule(ruleA);

        // RuleB
        Condition ruleBCondition01 = new OpenAccountInTimeCondition(7*24);
        Condition ruleBCondition02 = new ReceiveMoneyCondition(100000);
        CheckCondition ruleBCheckCondition = new CheckNumber(5);

        ruleB.addCondition(ruleBCondition01);
        ruleB.addCondition(ruleBCondition02);
        ruleB.addCheckCondition(ruleBCheckCondition.getCheckCondition());
        this.ruleEngineManager.addRule(ruleB);

    }

    public void setUpRuntimeRules(Date requestTime){
        log.info("[RULE ENGINE] set up runtime rules...");

        // RuleC
        Condition ruleCCondition01 = new RequesDateInTimeCondition(2, requestTime);
        Condition ruleCCondition02 = new ReceiveMoneyCondition(50000);
        CheckCondition ruleCCheckCondition = new CheckNumber(3);

        ruleC.addCondition(ruleCCondition01);
        ruleC.addCondition(ruleCCondition02);
        ruleC.addCheckCondition(ruleCCheckCondition.getCheckCondition());
        this.ruleEngineManager.addRule(ruleC);

    }
}
