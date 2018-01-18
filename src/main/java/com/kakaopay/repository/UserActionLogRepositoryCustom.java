package com.kakaopay.repository;

import com.kakaopay.entity.UserActionLog;

import java.util.List;

public interface UserActionLogRepositoryCustom {

    List<UserActionLog> test(Long userId);
}
