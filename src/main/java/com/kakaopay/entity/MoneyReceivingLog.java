package com.kakaopay.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "money_receiving_log")
public class MoneyReceivingLog implements Serializable {

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

    @Column(name = "balance_before_receiving")
    @Getter
    private Integer balanceBeforeReceiving;

    @Column(name = "sending_account_number")
    @Getter
    private String sendingAccountNumber;

    @Column(name = "sending_user_id")
    @Getter
    private Long sendingUserId;

    @Column(name = "receiving_money")
    @Getter
    private Integer receivingMoney;


}
