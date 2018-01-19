package com.kakaopay.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "service_account_log")
public class ServiceAccountLog implements Serializable {

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
}
