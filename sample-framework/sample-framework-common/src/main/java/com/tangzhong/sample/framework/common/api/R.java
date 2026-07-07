package com.tangzhong.sample.framework.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:27
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    public static <T> R<T> of(boolean success){
        return success ? success() : fail("操作失败");
    }

    public static <T> R<T> success(){
        return success("操作成功");
    }

    public static <T> R<T> success(String message){
        return build(200, message);
    }

    public static <T> R<T> success(T data){
        R<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> R<T> fail(String message){
        return fail(500, message);
    }

    public static <T> R<T> fail(Integer code, String message){
        return build(code, message);
    }

    private static <T> R<T> build(Integer code, String message){
        R<T> result = new R<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}