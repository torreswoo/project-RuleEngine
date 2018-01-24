package com.kakaopay.service;

import com.kakaopay.config.RuleConfig;
import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.ruleEngine.RuleEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RuleEngineManagerTest {

    @InjectMocks
    private RuleEngineManager ruleEngineManager;

    @Mock
    private RuleConfig ruleConfig;

    @Mock
    private RuleEngine ruleEngine;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        assertThat(ruleEngineManager).isNotNull();
        assertThat(ruleConfig).isNotNull();
        assertThat(ruleEngine).isNotNull();
    }


    @Test
    public void start() throws Exception {

        // given
        Long userId = new Long(10021);
        Date requestTime = new Date(); requestTime.setTime(new Long(1230021));
        List<UserActionLog> userActionLogList = new ArrayList<UserActionLog>();
        when(ruleEngineManager.getKakaoRuleEngine().getFDSresult()).thenReturn("RuleB");


        // when
        String result = ruleEngineManager.startFDS(userActionLogList, requestTime);

        // then
        assertThat(result).isEqualTo("RuleB");

    }

}