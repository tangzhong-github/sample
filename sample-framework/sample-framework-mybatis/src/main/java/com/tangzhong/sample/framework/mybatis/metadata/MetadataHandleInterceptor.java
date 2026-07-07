package com.tangzhong.sample.framework.mybatis.metadata;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 元数据处理拦截器
 * @author tangzhong
 * @date   2025-10-24 11:48
 * @since  V1.0.0
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
})
public class MetadataHandleInterceptor implements InnerInterceptor {

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        SqlCommandType sqlCommandType = ms.getSqlCommandType();
        Class<?> targetClazz;
        List<?> targetObjList;
        if(parameter instanceof List<?> list){
            targetClazz = list.getFirst().getClass();
            targetObjList = list;
        }else{
            targetClazz = parameter.getClass();
            targetObjList = Collections.singletonList(parameter);
        }
        List<Field> metadataFields = this.getMetadataFields(targetClazz, sqlCommandType);
        this.initMetadataFieldValue(targetObjList, metadataFields);
    }

    private List<Field> getMetadataFields(Class<?> targetClazz, SqlCommandType sqlCommandType){
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(targetClazz, field->{
            if(field.isAnnotationPresent(Metadata.class)){
                Metadata metadataAnnotation = field.getAnnotation(Metadata.class);
                if(Arrays.asList(metadataAnnotation.executionCommands()).contains(sqlCommandType)){
                    fields.add( field);
                }
            }
        });
        return fields;
    }

    private void initMetadataFieldValue(List<?> targetObjList, List<Field> metadataFields){
        if(!metadataFields.isEmpty()){
            for (Object targetObj : targetObjList) {
                metadataFields.forEach(field->{
                    Metadata metadataAnnotation = field.getAnnotation(Metadata.class);
                    Object fieldValue = MetadataGeneratorFactory.get(metadataAnnotation.type()).generate();
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, targetObj, fieldValue);
                });
            }
        }
    }

}