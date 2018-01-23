package com.kakaopay.service.ruleEngine;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;
import com.kakaopay.service.rule.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class KakaoMoneyRuleEngineWorker implements Callable<Map<String, Boolean>> {

    private Rule rule;
    private List<UserActionLog> userActionLogList;
    private Map<String, Boolean> ruleFDSMap;

    public KakaoMoneyRuleEngineWorker(Rule rule, List<UserActionLog> userActionLogList){
        this.rule = rule;
        this.userActionLogList = userActionLogList;
        this.ruleFDSMap = new ConcurrentHashMap<String, Boolean>();
    }


    @Override
    public Map<String, Boolean> call() throws Exception {
        log.info(" [ WORKER | {} check start ]", rule.getRuleName());

        for (Condition condition : rule.getConditionList()){
            if( userActionLogList != null)
                userActionLogList = (List<UserActionLog>)condition.applyCondition(userActionLogList);
        }

        ruleFDSMap.put(
            rule.getRuleName(),
            rule.checkFDS(userActionLogList)
        );

        return ruleFDSMap;
    }
}
