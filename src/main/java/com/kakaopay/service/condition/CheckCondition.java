package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;

import java.util.List;
import java.util.function.Predicate;

public interface CheckCondition<T> {

    Predicate<T> getCheckCondition();

}
