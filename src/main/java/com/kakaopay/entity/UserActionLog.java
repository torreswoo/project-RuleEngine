package com.kakaopay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_action_log")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserActionLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    private Integer id;

    @Column(name = "action_time")
    @Getter
    private Date actionTime;

    @Column(name = "user_id")
    @Getter
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private ServiceAccountLog serviceAccountLog;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private TransferLog transferLog;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private MoneyChargingLog moneyChargingLog;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private MoneyReceivingLog moneyReceivingLog;
}
