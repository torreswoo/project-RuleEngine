package com.kakaopay.repository;

import com.kakaopay.entity.*;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.Date;
import java.util.List;

public class UserActionLogRepositoryImpl
    extends QueryDslRepositorySupport
    implements UserActionLogRepositoryCustom {

    public UserActionLogRepositoryImpl(){
        super(UserActionLog.class);
    }

    @Override
    public List<UserActionLog> test(Long userId, Date testTime) throws Exception{

        QUserActionLog qUserActionLog = QUserActionLog.userActionLog;

        QServiceAccountLog qServiceAccountLog = QServiceAccountLog.serviceAccountLog;
        QMoneyReceivingLog qMoneyReceivingLog = QMoneyReceivingLog.moneyReceivingLog;
        QMoneyChargingLog qMoneyChargingLog = QMoneyChargingLog.moneyChargingLog;
        QTransferLog qTransferLog = QTransferLog.transferLog;

        JPQLQuery query =
            from(qUserActionLog)
                .leftJoin(qUserActionLog.serviceAccountLogs, qServiceAccountLog)
                .fetchJoin()
                .leftJoin(qUserActionLog.moneyReceivingLogs, qMoneyReceivingLog)
                .fetchJoin()
                .leftJoin(qUserActionLog.moneyChargingLogs, qMoneyChargingLog)
                .fetchJoin()
                .leftJoin(qUserActionLog.transferLogs, qTransferLog)
                .fetchJoin()
                .where(qUserActionLog.userId.eq(userId));

        return query.fetch();

    }


}
