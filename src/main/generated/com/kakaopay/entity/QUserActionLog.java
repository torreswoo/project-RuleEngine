package com.kakaopay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserActionLog is a Querydsl query type for UserActionLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserActionLog extends EntityPathBase<UserActionLog> {

    private static final long serialVersionUID = 1332452436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserActionLog userActionLog = new QUserActionLog("userActionLog");

    public final DateTimePath<java.util.Date> actionTime = createDateTime("actionTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMoneyChargingLog moneyChargingLog;

    public final QMoneyReceivingLog moneyReceivingLog;

    public final QServiceAccountLog serviceAccountLog;

    public final QTransferLog transferLog;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserActionLog(String variable) {
        this(UserActionLog.class, forVariable(variable), INITS);
    }

    public QUserActionLog(Path<? extends UserActionLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserActionLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserActionLog(PathMetadata metadata, PathInits inits) {
        this(UserActionLog.class, metadata, inits);
    }

    public QUserActionLog(Class<? extends UserActionLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.moneyChargingLog = inits.isInitialized("moneyChargingLog") ? new QMoneyChargingLog(forProperty("moneyChargingLog"), inits.get("moneyChargingLog")) : null;
        this.moneyReceivingLog = inits.isInitialized("moneyReceivingLog") ? new QMoneyReceivingLog(forProperty("moneyReceivingLog"), inits.get("moneyReceivingLog")) : null;
        this.serviceAccountLog = inits.isInitialized("serviceAccountLog") ? new QServiceAccountLog(forProperty("serviceAccountLog"), inits.get("serviceAccountLog")) : null;
        this.transferLog = inits.isInitialized("transferLog") ? new QTransferLog(forProperty("transferLog"), inits.get("transferLog")) : null;
    }

}

