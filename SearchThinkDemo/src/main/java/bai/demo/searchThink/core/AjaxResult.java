package bai.demo.searchThink.core;

import java.util.HashMap;
import java.util.Objects;

public class AjaxResult extends HashMap<Object, Object> {
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    public static final String DEFAULT_SUCCESS_MSG = "操作成功！";
    public static final String DEFAULT_ERROR_MSG = "操作失败！";

    AjaxResult(int code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if (Objects.nonNull(data))
            super.put(DATA, data);
    }

    public static AjaxResult successMsg(String msg) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, null);
    }

    public static AjaxResult successMsg() {
        return new AjaxResult(HttpStatus.SUCCESS, DEFAULT_SUCCESS_MSG, null);
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, DEFAULT_SUCCESS_MSG, data);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    public static AjaxResult success(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    public static AjaxResult errorMsg(String msg) {
        return new AjaxResult(HttpStatus.ERROR, msg, null);
    }

    public static AjaxResult errorMsg() {
        return new AjaxResult(HttpStatus.ERROR, DEFAULT_ERROR_MSG, null);
    }

    public static AjaxResult errorMsg_400(String msg) {
        return new AjaxResult(HttpStatus.BAD_REQUEST, msg, null);
    }

    public static AjaxResult errorMsg_403() {
        return new AjaxResult(HttpStatus.FORBIDDEN, "token过期或不正确", null);
    }

    public static AjaxResult errorMsg(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    public static AjaxResult error(Object data) {
        return new AjaxResult(HttpStatus.ERROR, DEFAULT_ERROR_MSG, data);
    }

    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    public static AjaxResult error(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }
}
