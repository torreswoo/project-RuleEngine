package com.kakaopay.repository;

import com.kakaopay.entity.UserActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActionLogRepository
    extends JpaRepository<UserActionLog, Integer>, UserActionLogRepositoryCustom {

    List<UserActionLog> findAllByUserId(Long user_id);
}
