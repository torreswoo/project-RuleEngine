package com.kakaopay.repository;

import com.kakaopay.entity.*;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class UserActionLogRepositoryImpl
    extends QueryDslRepositorySupport
    implements UserActionLogRepositoryCustom {

    public UserActionLogRepositoryImpl(){
        super(UserActionLog.class);
    }

    @Override
    public List<UserActionLog> test(Long userId){
        QServiceAccountLog qServiceAccountLog = QServiceAccountLog.serviceAccountLog;
        QUserActionLog qUserActionLog = QUserActionLog.userActionLog;

        JPQLQuery query =
            from(qUserActionLog)
                .join(qUserActionLog.serviceAccountLogs, qServiceAccountLog)
                .fetchJoin()
                .where(qUserActionLog.userId.eq(userId));


        return query.fetch();

    }
}
