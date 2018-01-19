package com.kakaopay.service.rule;

import java.util.ArrayList;
import java.util.List;

public class KakaoMoneyRule implements Rule{

    private String ruleName;
    private List<Condition> conditionList = new ArrayList<Condition>();

    public KakaoMoneyRule(String ruleName){
        this.ruleName = ruleName;
    }

    @Override
    public void addCondition(Condition condition) {
        conditionList.add(condition);
    }

    @Override
    public String getRuleName() {
        return this.ruleName;
    }


}
