package com.kakaopay.service.ruleEngine;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;
import com.kakaopay.service.rule.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class KakaoMoneyRuleEngine implements RuleEngine {

    private Map<String, Rule> ruleMap = new HashMap<String, Rule>();
    private Map<String, Boolean> ruleFDSMap = new HashMap<String, Boolean>();

    private int ruleCount = 3;
    private ExecutorService ruleEngineProcessingThreadPool;

    public KakaoMoneyRuleEngine(){
        this.ruleEngineProcessingThreadPool = Executors.newFixedThreadPool(this.ruleCount);
    }

    @Override
    public void addRule(Rule rule) {
        ruleMap.put(rule.getRuleName(), rule);
    }

    @Override
    public void execute(List<UserActionLog> userActionLogs) throws Exception {


        List<CompletableFuture> completableFutureList = new ArrayList<CompletableFuture>();
        for (String key: this.ruleMap.keySet()){
            completableFutureList.add(executeRule(this.ruleMap.get(key), userActionLogs));
        }

        CompletableFuture
            .allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]))
            .join();

        int i=0;
        for (String key: this.ruleMap.keySet()){
            this.ruleFDSMap.put(key, ((Map<String, Boolean>)completableFutureList.get(i++).get()).get(key) );
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

    @Async
    public CompletableFuture executeRule(Rule rule, List<UserActionLog> userActionLogs) throws InterruptedException {

        List<UserActionLog> userActionLogList = userActionLogs;
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        log.info(" [ {} check start ]", rule.getRuleName());

        for (Condition condition : rule.getConditionList()){
            if( userActionLogList != null)
                userActionLogList = (List<UserActionLog>)condition.applyCondition(userActionLogList);
        }

        resultMap.put(
            rule.getRuleName(),
            rule.checkFDS(userActionLogList)
        );

        return CompletableFuture.completedFuture(resultMap);

    }



}

