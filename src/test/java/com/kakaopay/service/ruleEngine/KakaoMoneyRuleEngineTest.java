package com.kakaopay.service.ruleEngine;

import com.kakaopay.service.condition.*;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class KakaoMoneyRuleEngineTest {

    private RuleEngine ruleEngine;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        ruleEngine = new KakaoMoneyRuleEngine();

    }

    @Test
    public void execute() throws Exception {
    }

    @Test
    public void executeRule() throws Exception {
    }

    @Test
    public void addRule() throws Exception {

        // when - RuleA
        Rule ruleA = new KakaoMoneyRule("RuleA");
        Condition ruleACondition01 = new OpenAccountInTimeCondition(1);
        Condition ruleACondition02 = new ChargingMoneyCondition(2000000);
        CheckCondition ruleACheckCondition = new CheckBalance(1000);

        ruleA.addCondition(ruleACondition01);
        ruleA.addCondition(ruleACondition02);
        ruleA.addCheckCondition(ruleACheckCondition.getCheckCondition());

        // give
        ruleEngine.addRule(ruleA);

        // then
        Rule savedRuleA = ((KakaoMoneyRuleEngine)ruleEngine).getRuleMap().get("RuleA");
        assertThat(savedRuleA.getRuleName())
            .isEqualTo("RuleA");
        assertThat(savedRuleA.getConditionList().size())
            .isEqualTo(2);

    }

    @Test
    public void getFDSresult() throws Exception {

        // when
        Map<String, Boolean> ruleFDSMap = new HashMap<String, Boolean>();
        ruleFDSMap.put("RuleA", false);
        ruleFDSMap.put("RuleB", true);
        ruleFDSMap.put("RuleC", false);
        ((KakaoMoneyRuleEngine)ruleEngine).setRuleFDSMap(ruleFDSMap);

        // give
        String result = ruleEngine.getFDSresult();

        // then
        assertThat(result).isEqualTo("RuleB");
    }

}