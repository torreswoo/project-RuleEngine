package com.kakaopay.repository;

import com.kakaopay.entity.UserActionLog;

import java.util.Date;
import java.util.List;

public interface UserActionLogRepositoryCustom {

    List<UserActionLog> findAllUserActionLogByUserId(Long userId) throws Exception;
}
