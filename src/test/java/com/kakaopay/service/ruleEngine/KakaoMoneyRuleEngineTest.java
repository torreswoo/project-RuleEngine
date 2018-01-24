package com.kakaopay.service.ruleEngine;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
import com.kakaopay.service.condition.*;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class KakaoMoneyRuleEngineTest {

    private RuleEngine ruleEngine;

    @Autowired
    private UserActionLogRepository userActionLogRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        assertThat(userActionLogRepository).isNotNull();
        ruleEngine = new KakaoMoneyRuleEngine(3);

    }

    @Test
    public void execute() throws Exception {

        // when
        // when - add RuleA
        Rule ruleA = new KakaoMoneyRule("RuleA");
        Condition ruleACondition01 = new OpenAccountInTimeCondition(1);
        Condition ruleACondition02 = new ChargingMoneyCondition(2000000);
        CheckCondition ruleACheckCondition = new CheckBalance(1000);

        ruleA.addCondition(ruleACondition01);
        ruleA.addCondition(ruleACondition02);
        ruleA.addCheckCondition(ruleACheckCondition.getCheckCondition());
        ruleEngine.addRule(ruleA);

        // when - add RuleB
        Rule ruleB = new KakaoMoneyRule("RuleB");
        Condition ruleBCondition01 = new OpenAccountInTimeCondition(7*24);
        Condition ruleBCondition02 = new ReceiveMoneyCondition(100000);
        CheckCondition ruleBCheckCondition = new CheckNumber(5);

        ruleB.addCondition(ruleBCondition01);
        ruleB.addCondition(ruleBCondition02);
        ruleB.addCheckCondition(ruleBCheckCondition.getCheckCondition());
        ruleEngine.addRule(ruleB);

        // when - add RuleC
        Rule ruleC = new KakaoMoneyRule("RuleC");
        Condition ruleCCondition01 = new RequesDateInTimeCondition(2, (new Date()));
        Condition ruleCCondition02 = new ReceiveMoneyCondition(50000);
        CheckCondition ruleCCheckCondition = new CheckNumber(3);

        ruleC.addCondition(ruleCCondition01);
        ruleC.addCondition(ruleCCondition02);
        ruleC.addCheckCondition(ruleCCheckCondition.getCheckCondition());
        ruleEngine.addRule(ruleC);

        // when - add userActionLogs
        List<UserActionLog> userActionLogs = userActionLogRepository.findAllUserActionLogByUserId(new Long(10021));

        // given
        ruleEngine.execute(userActionLogs);

        // then
        assertThat(ruleEngine.getFDSresult())
            .isEqualTo("RuleB");


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