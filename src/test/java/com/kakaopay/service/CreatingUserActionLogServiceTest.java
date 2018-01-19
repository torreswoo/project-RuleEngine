package com.kakaopay.service;

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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreatingUserActionLogServiceTest {

    @InjectMocks
    private CreatingUserActionLogService creatingUserActionLogService;

    @Mock
    private UserActionLogRepository userActionLogRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        assertThat(creatingUserActionLogService).isNotNull();
        assertThat(userActionLogRepository).isNotNull();
    }

    @Test
    public void checkFDSUsingRuleEngine() throws Exception {


        // given
        Long userId = new Long(10023);
        Date testTime = new Date();

        // when
//        creatingUserActionLogService.checkFDSUsingRuleEngine(userId, testTime);

        // then
//        assertThat(userActionLogs.size()).isEqualTo(8);
    }

}