package com.kakaopay.service.ruleEngine;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;
import com.kakaopay.service.rule.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class KakaoMoneyRuleEngine implements RuleEngine {

    private Map<String, Rule> ruleMap = new HashMap<String, Rule>();
    private Map<String, Boolean> ruleFDSMap = new HashMap<String, Boolean>();

    public KakaoMoneyRuleEngine(){
    }

    @Override
    public void addRule(Rule rule) {
        ruleMap.put(rule.getRuleName(), rule);
    }

    @Override
    public void execute(List<UserActionLog> userActionLogs) {
        // TODO: concurrency & applyFilter conditions in each rules
        List<UserActionLog> userActionLogList = userActionLogs;
        for ( Map.Entry<String, Rule> entry : this.ruleMap.entrySet() ){
            Rule rule = entry.getValue();
            log.info("-----------[ {} check start ]", rule.getRuleName());

            for (Condition condition : rule.getConditionList()){
                if( userActionLogList != null) userActionLogList = condition.applyCondition(userActionLogList);
            }

            this.ruleFDSMap.put(
                rule.getRuleName(),
                rule.checkFDS(userActionLogList)
            );
        }
    }

    @Override
    public String resultFDSfromRuleEngine() {
        String result="";

        for ( Map.Entry<String, Boolean> entry : this.ruleFDSMap.entrySet() ){
            if(entry.getValue() == true){
                result = result + entry.getKey();
            }
        }

        return result;
    }


}

