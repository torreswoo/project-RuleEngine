package com.kakaopay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_action_log")
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private List<ServiceAccountLog> serviceAccountLogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private List<TransferLog> transferLogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private List<MoneyChargingLog> moneyChargingLogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private List<MoneyReceivingLog> moneyReceivingLogs;
}
