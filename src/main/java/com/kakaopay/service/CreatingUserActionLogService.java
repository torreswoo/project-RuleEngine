package com.kakaopay.service;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatingUserActionLogService {
    @Autowired
    private UserActionLogRepository userActionLogRepository;

    public List<UserActionLog> findAllUserActionLogByUserId(Long userId){


        return userActionLogRepository.findAllByUserId(userId);
    }

    public int test(Long userId){

        List<UserActionLog> userActionLogList = userActionLogRepository.test(userId);


        return userActionLogList.size();
    }

}
