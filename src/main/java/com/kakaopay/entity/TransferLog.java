package com.kakaopay.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transfer_log")
public class TransferLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_action_log_id")
    @Getter
    private UserActionLog userActionLog;

    @Column(name = "transfer_account_number")
    @Getter
    private String transferAccountNumber;

    @Column(name = "balance_before_transfer")
    @Getter
    private Integer balanceBeforeTransfer;

    @Column(name = "receiving_account_number")
    @Getter
    private String receivingAccountNumber;

    @Column(name = "receiving_user_id")
    @Getter
    private Long receivingUserId;

    @Column(name = "transter_money")
    @Getter
    private Integer transferMoney;

}
