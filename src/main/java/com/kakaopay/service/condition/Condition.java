package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;

import java.util.List;

public interface Condition {

    List<UserActionLog> applyCondition(List<UserActionLog> userActionLogList);
}
