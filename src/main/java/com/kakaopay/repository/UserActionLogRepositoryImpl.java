package com.kakaopay.repository;

import com.kakaopay.entity.UserActionLog;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class UserActionLogRepositoryImpl
    extends QueryDslRepositorySupport
    implements UserActionLogRepositoryCustom {

    public UserActionLogRepositoryImpl(){
        super(UserActionLog.class);
    }
}
