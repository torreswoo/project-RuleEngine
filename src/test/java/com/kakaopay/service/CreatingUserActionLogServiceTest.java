package com.kakaopay.service;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
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
public class CreatingUserActionLogServiceTest {

    @InjectMocks
    private CreatingUserActionLogService creatingUserActionLogService;

    @Mock
    private RuleEngineManager ruleEngineManager;

    @Mock
    private UserActionLogRepository userActionLogRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        assertThat(creatingUserActionLogService).isNotNull();
        assertThat(ruleEngineManager).isNotNull();
        assertThat(userActionLogRepository).isNotNull();
    }

    @Test
    public void checkFDSUsingRuleEngine() throws Exception {

        // given
        Long userId = new Long(10021);
        Date requestTime = new Date(); requestTime.setTime(new Long(1230021));
        List<UserActionLog> userActionLogList = new ArrayList<UserActionLog>();
        when(creatingUserActionLogService.findAllUserActionLogByUserId(userId)).thenReturn(userActionLogList);
        when(ruleEngineManager.start(userActionLogList, requestTime)).thenReturn("RuleB");

        // when
        String result = creatingUserActionLogService.checkFDSUsingRuleEngine(userId, requestTime);

        // then
        assertThat(result).isEqualTo("RuleB");
    }

}