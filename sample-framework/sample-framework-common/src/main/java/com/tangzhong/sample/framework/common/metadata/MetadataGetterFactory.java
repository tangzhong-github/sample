package com.tangzhong.sample.framework.common.metadata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangzhong
 * @date   2026-07-06 14:40
 * @since  V1.0.0
 */
public class MetadataGetterFactory {

    private static final Map<String, MetadataGetter<?>> METADATA_GETTER_MAP = new ConcurrentHashMap<>();

    public static void register(MetadataGetter<?> getter){
        METADATA_GETTER_MAP.put(getter.metadataType(), getter);
    }

    @SuppressWarnings("unchecked")
    public static <T> MetadataGetter<T> get(String symbol){
        MetadataGetter<?> getter = METADATA_GETTER_MAP.get(symbol);
        return ((MetadataGetter<T>) getter);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String symbol){
        MetadataGetter<?> getter = METADATA_GETTER_MAP.get(symbol);
        return ((MetadataGetter<T>) getter).get();
    }

}