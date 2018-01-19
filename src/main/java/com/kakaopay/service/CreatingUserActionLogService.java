package com.kakaopay.service;

import com.kakaopay.entity.ServiceAccountLog;
import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
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

    public List<UserActionLog> findAllUserActionLogByUserId(Long userId){

        return userActionLogRepository.findAllByUserId(userId);
    }

    public List<UserActionLog> test(Long userId, Date testTime) throws Exception{
        List<UserActionLog> userActionLogList = userActionLogRepository.test(userId, testTime);
        return userActionLogList;
    }

    //
    public String checkFDSUsingRuleEngine(Long userId, Date testTime) throws Exception{

        // 01. user_id get actionlog
        List<UserActionLog> userActionLogList = this.test(userId, testTime);
        log.info("---test {}", userActionLogList);

        // 02. check Rules
        // 02-01. RuleB
        List<UserActionLog> dateLog = userActionLogList
            .stream()
            .filter(userActionLog -> userActionLog.getServiceAccountLog() != null)
            .collect(Collectors.toList());
        Date startDate = dateLog.get(0).getActionTime();
        Date endDate = new Date(dateLog.get(0).getActionTime().getTime() + (1000 * 60 * 60 * 24 *7));
        log.info("---test | {}, {}", startDate, endDate);

        //
        List<UserActionLog> filteredLog01 = userActionLogList
            .stream()
            .filter(userActionLog ->
                    userActionLog.getActionTime().compareTo(startDate) >= 0 &&
                    userActionLog.getActionTime().compareTo(endDate) <= 0)
            .collect(Collectors.toList());
        log.info("---test filteredLog01 | size : {}", filteredLog01.size());
        //
        List<UserActionLog> filteredLog02 = filteredLog01
            .stream()
            .filter(userActionLog ->
                    userActionLog.getMoneyReceivingLog() != null &&
                    userActionLog.getMoneyReceivingLog().getReceivingMoney() >= 100000)
            .collect(Collectors.toList());

        //
        log.info("---test filteredLog02 | size : {}", filteredLog02.size());
        if (filteredLog02.size() >= 5) {
            return "true";
        }


        return "test";
    }

}
