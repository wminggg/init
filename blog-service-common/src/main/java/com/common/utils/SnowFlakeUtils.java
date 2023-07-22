package com.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SnowFlakeUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class SnowFlakeUtils {

    /**
     * 单例对象
     */
    private static final SnowFlakeUtils SNOW_FLAKE_UTILS = new SnowFlakeUtils();

    /**
     * 私有构造方法
     */
    private SnowFlakeUtils() {}

    /**
     * 获取 SnowFlakeUtils 实例
     *
     * @return SnowFlakeUtils 实例
     */
    public static SnowFlakeUtils getInstance() {
        return SNOW_FLAKE_UTILS;
    }

    /**
     * 序列号，同一毫秒内用此参数来控制并发
     */
    private long sequence = 0L;

    /**
     * 上一次生成编号的时间串，格式：yyMMddHHmmssSSS
     */
    private String lastTime = "";

    /**
     * 获取编号
     *
     * @return 编号字符串
     */
    public synchronized Long getNum() {
        // 获取当前时间串，格式：yyMMddHHmmssSSS
        String nowTime = getTime();
        // 机器编号，这里假装获取到的机器编号是2。实际项目中可从配置文件中读取
        String machineId = "01";
        // 本次和上次不是同一毫秒，直接生成编号返回
        if (!lastTime.equals(nowTime)) {
            // 重置序列号，方便下次使用
            sequence = 0L;
            // 更新时间串，方便下次使用
            lastTime = nowTime;
            return Long.parseLong(nowTime + machineId + sequence);
        }
        // 序列号没有达到最大值，直接生成编号返回
        if (sequence < 99) {
            sequence = sequence + 1;
            return Long.parseLong(nowTime + machineId + sequence);
        }
        // 序列号达到最大值，需要等待下一毫秒的到来
        while (lastTime.equals(nowTime)) {
            nowTime = getTime();
        }
        // 重置序列号，方便下次使用
        sequence = 0L;
        // 更新时间串，方便下次使用
        lastTime = nowTime;
        return Long.parseLong(nowTime + machineId + sequence);
    }

    /**
     * 获取当前时间串，格式：yyMMddHHmmssSSS
     *
     * @return 当前时间串
     */
    private String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
    }
}


