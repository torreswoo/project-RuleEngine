package com.kakaopay.service.ruleEngine;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;
import com.kakaopay.service.rule.Rule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class KakaoMoneyRuleEngine implements RuleEngine {

    private Map<String, Rule> ruleMap;
    private Map<String, Boolean> ruleFDSMap;
    private ExecutorService execService;
    private int ruleCount;

    public KakaoMoneyRuleEngine(int ruleCount){
        this.ruleMap = new HashMap<String, Rule>();
        this.ruleFDSMap = new ConcurrentHashMap<String, Boolean>();
        this.ruleCount = ruleCount;
        this.execService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void execute(List<UserActionLog> userActionLogs) throws Exception {

        // execute - threadpool & worker
        List<Future<Map<String, Boolean>>> futureList = new ArrayList<Future<Map<String, Boolean>>>();
        for (String key: this.ruleMap.keySet()){
            futureList.add(
                execService.submit(
                    new KakaoMoneyRuleEngineWorker(this.ruleMap.get(key), userActionLogs )
                )
            );
        }

        for (Future<Map<String, Boolean>> future : futureList) {
            ruleFDSMap.putAll(future.get());
        }

    }

    @Override
    public String getFDSresult() {
        String result="";
        for ( Map.Entry<String, Boolean> entry : this.ruleFDSMap.entrySet() ){
            if(entry.getValue() == true){
                result = result + entry.getKey();
            }
        }
        return result;
    }

    @Override
    public void addRule(Rule rule) {
        ruleMap.put(rule.getRuleName(), rule);
    }

    @Override
    public void shutdown(){
        execService.shutdownNow();
    }

    public Map<String, Rule> getRuleMap(){
        return this.ruleMap;
    }

    public Map<String, Boolean> getRuleFDSMap(){
        return this.ruleFDSMap;
    }

    public void setRuleFDSMap(Map<String, Boolean> ruleFDSMap){
        this.ruleFDSMap = ruleFDSMap;
    }



}

