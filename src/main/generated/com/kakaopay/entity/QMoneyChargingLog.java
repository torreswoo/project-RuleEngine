package com.kakaopay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoneyChargingLog is a Querydsl query type for MoneyChargingLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoneyChargingLog extends EntityPathBase<MoneyChargingLog> {

    private static final long serialVersionUID = -1889152542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoneyChargingLog moneyChargingLog = new QMoneyChargingLog("moneyChargingLog");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath bankAccountNumber = createString("bankAccountNumber");

    public final NumberPath<Integer> chargingMoney = createNumber("chargingMoney", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QUserActionLog userActionLog;

    public QMoneyChargingLog(String variable) {
        this(MoneyChargingLog.class, forVariable(variable), INITS);
    }

    public QMoneyChargingLog(Path<? extends MoneyChargingLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoneyChargingLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoneyChargingLog(PathMetadata metadata, PathInits inits) {
        this(MoneyChargingLog.class, metadata, inits);
    }

    public QMoneyChargingLog(Class<? extends MoneyChargingLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userActionLog = inits.isInitialized("userActionLog") ? new QUserActionLog(forProperty("userActionLog"), inits.get("userActionLog")) : null;
    }

}

