package com.kakaopay.service;

import com.kakaopay.entity.ServiceAccountLog;
import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
import com.kakaopay.service.condition.Condition;
import com.kakaopay.service.condition.OpenAccountInTimeCondition;
import com.kakaopay.service.condition.ReceiveMoneyCondition;
import com.kakaopay.service.rule.KakaoMoneyRule;
import com.kakaopay.service.rule.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CreatingUserActionLogService {
    @Autowired
    private UserActionLogRepository userActionLogRepository;


    public List<UserActionLog> findAllUserActionLogByUserId(Long userId) throws Exception{
        List<UserActionLog> userActionLogList = userActionLogRepository.findAllUserActionLogByUserId(userId);
        return userActionLogList;
    }


    public String checkFDSUsingRuleEngine(Long userId, Date requestTime) throws Exception{

        List<UserActionLog> userActionLogList = this.findAllUserActionLogByUserId(userId);

        log.info("-- FDS START {}", userActionLogList);
        RuleEngineManager ruleEngineManager = new RuleEngineManager();
        String result = ruleEngineManager.start(userActionLogList);
        log.info("-- FDS result : {}", result);

        return result;
    }

}
