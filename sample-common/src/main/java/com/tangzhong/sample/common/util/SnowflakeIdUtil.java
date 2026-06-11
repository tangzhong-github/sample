package com.tangzhong.sample.common.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 16位长度雪花ID生成器
 * 生成Long类型的16位ID，基于当前时间戳生成
 * @author tangzhong
 * @date   2025-08-27 15:16
 * @since  V1.0.0
 */
public class SnowflakeIdUtil{

    /**
     * 起始时间戳 (2020-01-01 00:00:00)
     * 使用更早的起始时间，确保时间戳差值足够大，生成16位ID
     */
    private final static long START_TIMESTAMP = 1577836800000L;

    /**
     * 各部分位数配置
     * 优化方案：调整位数分配，确保生成的ID在16位十进制范围内
     * 时间戳(39位) + 机器ID(10位) + 序列号(12位) = 61位
     * 但通过相对时间戳和掩码控制，确保数值在 [10^15, 10^16) 范围内
     */
    private final static long MACHINE_BIT = 10;
    private final static long SEQUENCE_BIT = 12;

    /**
     * 各部分最大值
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 左移位数
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 16位ID的范围约束
     * 最小值：1000000000000000 (10^15)
     * 最大值：9999999999999999 (10^16 - 1)
     */
    private final static long MIN_16_DIGIT = 1000000000000000L;
    private final static long MAX_16_DIGIT = 9999999999999999L;

    /**
     * 机器ID
     */
    private final long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     * @param machineId 机器ID (0-63)
     */
    public SnowflakeIdUtil(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId must be between 0 and " + MAX_MACHINE_NUM);
        }
        this.machineId = machineId;
    }

    /**
     * 生成下一个16位长度的雪花ID
     * @return Long类型的16位ID
     */
    public synchronized Long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        // 时钟回退检测
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        // 同一毫秒内序列号递增
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTimestamp = getNextMill();
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 计算时间戳差值（相对时间戳，减少位数）
        long timeDiff = currentTimestamp - START_TIMESTAMP;

        // 生成原始雪花ID
        long rawId = (timeDiff << TIMESTAMP_LEFT) | (machineId << MACHINE_LEFT) | sequence;

        // 通过取模和偏移确保ID始终为16位
        // 1. 先取模控制在一定范围内 (0 ~ MAX_16_DIGIT - MIN_16_DIGIT)
        long range = MAX_16_DIGIT - MIN_16_DIGIT + 1;
        long id = MIN_16_DIGIT + (rawId % range);

        return id;
    }

    /**
     * 等待下一毫秒
     * @return 下一毫秒时间戳
     */
    private long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastTimestamp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    /**
     * 获取默认实例
     * @return SnowflakeIdGenerator16实例
     */
    public static SnowflakeIdUtil getInstance() {
        return SnowflakeIdGenerator16Holder.INSTANCE;
    }

    /**
     * 单例持有者
     */
    private static class SnowflakeIdGenerator16Holder {
        private static final SnowflakeIdUtil INSTANCE = new SnowflakeIdUtil(getMachineNum());
    }

    /**
     * 获取机器编号
     * @return 机器编号
     */
    private static long getMachineNum() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(address);
            if (network == null) {
                return ThreadLocalRandom.current().nextLong(MAX_MACHINE_NUM + 1);
            }
            byte[] mac = network.getHardwareAddress();
            if (mac == null) {
                return ThreadLocalRandom.current().nextLong(MAX_MACHINE_NUM + 1);
            }
            long machineId = ((mac[0] & 0x0FL) << 2) | ((mac[1] & 0xC0L) >> 6);
            return machineId & MAX_MACHINE_NUM;
        } catch (Exception e) {
            return getProcessId() & MAX_MACHINE_NUM;
        }
    }

    /**
     * 获取进程ID
     * @return 进程ID
     */
    private static long getProcessId() {
        try {
            String processName = ManagementFactory.getRuntimeMXBean().getName();
            return Long.parseLong(processName.split("@")[0]) % (MAX_MACHINE_NUM + 1);
        } catch (Exception e) {
            return ThreadLocalRandom.current().nextLong(MAX_MACHINE_NUM + 1);
        }
    }
}