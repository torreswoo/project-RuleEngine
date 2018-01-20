package com.kakaopay.service.rule;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class KakaoMoneyRule implements Rule{

    private String ruleName;
    private List<Condition> conditionList = new ArrayList<Condition>();
    private Predicate<List<UserActionLog>> checkCondition;

    public KakaoMoneyRule(String ruleName){
        this.ruleName = ruleName;
    }

    @Override
    public boolean checkFDS(List<UserActionLog> userActionLogList) {
        if(userActionLogList == null)
            return false;
        return this.checkCondition.test(userActionLogList);
    }

    @Override
    public void addCheckCondition(Predicate<List<UserActionLog>> checkCondition) {
        this.checkCondition = checkCondition;
    }

    @Override
    public void addCondition(Condition condition) {
        conditionList.add(condition);
    }

    @Override
    public List<Condition> getConditionList() {
        return this.conditionList;
    }

    @Override
    public String getRuleName() {
        return this.ruleName;
    }

    @Override
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }





}
