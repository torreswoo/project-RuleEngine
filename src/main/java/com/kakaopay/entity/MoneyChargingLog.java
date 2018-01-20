package com.kakaopay.entity;


import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "money_charging_log")
public class MoneyChargingLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_action_log_id")
    @Getter
    private UserActionLog userActionLog;

    @Column(name = "account_number")
    @Getter
    private String accountNumber;

    @Column(name = "charging_money")
    @Getter
    private Integer chargingMoney;

    @Column(name = "bank_account_number")
    @Getter
    private String bankAccountNumber;



}
