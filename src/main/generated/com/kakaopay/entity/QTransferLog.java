package com.kakaopay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTransferLog is a Querydsl query type for TransferLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransferLog extends EntityPathBase<TransferLog> {

    private static final long serialVersionUID = -2009679286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransferLog transferLog = new QTransferLog("transferLog");

    public final NumberPath<Integer> balanceBeforeTransfer = createNumber("balanceBeforeTransfer", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath receivingAccountNumber = createString("receivingAccountNumber");

    public final NumberPath<Long> receivingUserId = createNumber("receivingUserId", Long.class);

    public final StringPath transferAccountNumber = createString("transferAccountNumber");

    public final NumberPath<Integer> transferMoney = createNumber("transferMoney", Integer.class);

    public final QUserActionLog userActionLog;

    public QTransferLog(String variable) {
        this(TransferLog.class, forVariable(variable), INITS);
    }

    public QTransferLog(Path<? extends TransferLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransferLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransferLog(PathMetadata metadata, PathInits inits) {
        this(TransferLog.class, metadata, inits);
    }

    public QTransferLog(Class<? extends TransferLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userActionLog = inits.isInitialized("userActionLog") ? new QUserActionLog(forProperty("userActionLog"), inits.get("userActionLog")) : null;
    }

}

