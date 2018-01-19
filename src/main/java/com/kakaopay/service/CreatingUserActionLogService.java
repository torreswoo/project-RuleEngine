package com.kakaopay.service;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
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

}
