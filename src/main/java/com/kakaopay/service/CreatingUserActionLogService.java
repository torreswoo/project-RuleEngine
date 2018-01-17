package com.kakaopay.service;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.repository.UserActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreatingUserActionLogService {
    @Autowired
    private UserActionLogRepository userActionLogRepository;

    public List<UserActionLog> findAllUserActionLogByUserId(Long userId){

        return userActionLogRepository.findAllByUserId(userId);
    }

}
