package com.tangzhong.sample.framework.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author tangzhong
 * @date   2026-06-05 14:56
 * @since  V1.0.0
 */
public class JsonUtil {
    /**
     * 全局单例 ObjectMapper（Spring 也是如此）
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // 日期格式
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    static {
        // 1. 注册 Java8 时间模块（必须！否则 LocalDateTime 序列化报错）
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // LocalDateTime 序列化 & 反序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));

        // LocalDate 序列化 & 反序列化
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));

        OBJECT_MAPPER.registerModule(javaTimeModule);

        // 2. 日期不转时间戳
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 3. 忽略不存在的字段（最常用）
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 4. 空对象不报错
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    // ====================== 对象转 JSON ======================
    public static String toJsonString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("对象转JSON失败", e);
        }
    }

    // ====================== JSON转对象 ======================
    public static <T> T toObj(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON转对象失败", e);
        }
    }

    // ====================== JSON转List ======================
    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON转List失败", e);
        }
    }

    // ====================== JSON转Map ======================
    public static <K, V> Map<K, V> toMap(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<Map<K, V>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON转Map失败", e);
        }
    }

    // ====================== 泛型复杂类型 ======================
    public static <T> T toType(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON转泛型失败", e);
        }
    }
}