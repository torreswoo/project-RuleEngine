package com.kakaopay.service.condition;

public interface Condition<T>{

    T applyCondition(T value);
}
