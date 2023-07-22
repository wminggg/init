package com.common.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ObjectUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class ObjectUtils {

    private ObjectUtils() {
        // private constructor to prevent instantiation
    }

    public static <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }

    public static <T> void setOrDefault(T value, T defaultValue, Consumer<T> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        } else {
            setter.accept(defaultValue);
        }
    }

    public static <T> void setIfNotNullOrElse(T value, Consumer<T> setter, Runnable elseAction) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        } else {
            elseAction.run();
        }
    }

    public static <T> void setIfNotNullOrDefault(T value, T defaultValue, Consumer<T> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        } else {
            setter.accept(defaultValue);
        }
    }

    public static <T> void setIfNotNullOrCompute(T value, Supplier<T> defaultValueSupplier, Consumer<T> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        } else {
            T defaultValue = defaultValueSupplier.get();
            setter.accept(defaultValue);
        }
    }
}
