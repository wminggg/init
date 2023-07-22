package com.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;

import static java.lang.System.currentTimeMillis;

/**
 * DateTimeUtil
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class DateTimeUtil {
    private static final DateTimeFormatter FORMAT_STRING = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DateTimeUtil() {
    }

    public static long getStamp() {
        return currentTimeMillis();
    }

    public static LocalTime getLocalTime(Long timestamp) {
        return LocalTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static LocalDate getLocalDate(Long timestamp) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static LocalDateTime getLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static LocalDateTime generateTodayLocalDateTime(LocalTime localTime) {
        return LocalDateTime.of(LocalDate.now(), localTime);
    }

    public static Long dealRequestTime(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        } else {
            String dateString = date + " 00:00:00";
            LocalDateTime parse = LocalDateTime.parse(dateString, FORMAT_STRING);
            return LocalDateTime.from(parse).atZone(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        }
    }
}