package com.kakaopay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoneyReceivingLog is a Querydsl query type for MoneyReceivingLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoneyReceivingLog extends EntityPathBase<MoneyReceivingLog> {

    private static final long serialVersionUID = -360592555L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoneyReceivingLog moneyReceivingLog = new QMoneyReceivingLog("moneyReceivingLog");

    public final StringPath accountNumber = createString("accountNumber");

    public final NumberPath<Integer> balanceBeforeReceiving = createNumber("balanceBeforeReceiving", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> receivingMoney = createNumber("receivingMoney", Integer.class);

    public final StringPath sendingAccountNumber = createString("sendingAccountNumber");

    public final NumberPath<Long> sendingUserId = createNumber("sendingUserId", Long.class);

    public final QUserActionLog userActionLog;

    public QMoneyReceivingLog(String variable) {
        this(MoneyReceivingLog.class, forVariable(variable), INITS);
    }

    public QMoneyReceivingLog(Path<? extends MoneyReceivingLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoneyReceivingLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoneyReceivingLog(PathMetadata metadata, PathInits inits) {
        this(MoneyReceivingLog.class, metadata, inits);
    }

    public QMoneyReceivingLog(Class<? extends MoneyReceivingLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userActionLog = inits.isInitialized("userActionLog") ? new QUserActionLog(forProperty("userActionLog"), inits.get("userActionLog")) : null;
    }

}

