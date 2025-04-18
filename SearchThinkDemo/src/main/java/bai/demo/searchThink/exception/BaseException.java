package bai.demo.searchThink.exception;


import bai.demo.searchThink.core.HttpStatus;

import java.io.Serial;

public class BaseException extends Exception {
    public static final String PARAMS_ERROR = "参数错误";

    @Serial
    private static final long serialVersionUID = -5174376522312511076L;

    public String msg;
    public int code;

    public BaseException(String msg) {
        this.msg = msg;
        this.code = HttpStatus.BAD_REQUEST;
    }

    public BaseException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
