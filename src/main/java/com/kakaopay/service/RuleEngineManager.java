package com.kakaopay.service;

import com.kakaopay.entity.MoneyReceivingLog;
import com.kakaopay.entity.TransferLog;
import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.*;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import com.kakaopay.service.ruleEngine.KakaoMoneyRuleEngine;
import com.kakaopay.service.ruleEngine.RuleEngine;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RuleEngineManager {

    private RuleEngine kakaoRuleEngine;

    public RuleEngineManager(){
        this.kakaoRuleEngine = new KakaoMoneyRuleEngine();
    }

    public String start(List<UserActionLog> userActionLogList, Date requestTime){
        // setup Rules
        setUpRules(requestTime);

        // execute Rule Engine
        this.kakaoRuleEngine.execute(userActionLogList);
        return this.kakaoRuleEngine.resultFDSfromRuleEngine();

    }

    public void setUpRules(Date requestTime){

        // RuleA
        Condition ruleBCondition05 = new OpenAccountInTimeCondition(1);
        Condition ruleBCondition06 = new ChargingMoneyCondition(2000000);
        Rule ruleA = new KakaoMoneyRule("RuleA");
        ruleA.addCondition(ruleBCondition05);
        ruleA.addCondition(ruleBCondition06);
        ruleA.addCheckCondition((List<UserActionLog> userActionLogs) -> {
            for (UserActionLog userActionLog : userActionLogs) {
                TransferLog transferLog = userActionLog.getTransferLog();
                if(transferLog != null && transferLog.getBalanceBeforeTransfer() - transferLog.getTransferMoney() <= 1000 )
                    return true;
                MoneyReceivingLog moneyReceivingLog = userActionLog.getMoneyReceivingLog();
                if(moneyReceivingLog != null && moneyReceivingLog.getBalanceBeforeReceiving() + moneyReceivingLog.getReceivingMoney() <= 1000 )
                    return true;
            }
            return false;
        });
        this.kakaoRuleEngine.addRule(ruleA);

        // RuleB
        Condition ruleBCondition01 = new OpenAccountInTimeCondition(7*24);
        Condition ruleBCondition02 = new ReceiveMoneyCondition(100000);
        Rule ruleB = new KakaoMoneyRule("RuleB");
        ruleB.addCondition(ruleBCondition01);
        ruleB.addCondition(ruleBCondition02);
        ruleB.addCheckCondition((List<UserActionLog> userActionLogs) -> userActionLogs.size() >= 5);
        this.kakaoRuleEngine.addRule(ruleB);

        // RuleC
        Condition ruleBCondition03 = new RequesDateInTimeCondition(2, requestTime);
        Condition ruleBCondition04 = new ReceiveMoneyCondition(50000);
        Rule ruleC = new KakaoMoneyRule("RuleC");
        ruleC.addCondition(ruleBCondition03);
        ruleC.addCondition(ruleBCondition04);
        ruleC.addCheckCondition((List<UserActionLog> userActionLogs) -> userActionLogs.size() >= 3);
        this.kakaoRuleEngine.addRule(ruleC);

    }
}
