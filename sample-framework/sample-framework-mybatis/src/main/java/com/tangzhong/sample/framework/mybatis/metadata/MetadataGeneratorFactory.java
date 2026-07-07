package com.tangzhong.sample.framework.mybatis.metadata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 16:29
 * @since  V1.0.0
 */
public class MetadataGeneratorFactory {

   private static final Map<String, MetadataGenerator<?>> METADATA_GENERATOR_MAP = new ConcurrentHashMap<>();

   public static void register(MetadataGenerator<?> generator){
       METADATA_GENERATOR_MAP.put(generator.metaDataType(), generator);
   }

   public static MetadataGenerator<?> get(String metadataType){
       return METADATA_GENERATOR_MAP.get(metadataType);
   }

}
