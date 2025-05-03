package bai.demo.fileuploaddemo.domain;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-05-03 14:47
 * @description
 */
public class StdResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private Object data;

    public StdResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static StdResult success() {
        return new StdResult("200", "操作成功", null);
    }

    public static StdResult success(Object data) {
        return new StdResult("200", "操作成功", data);
    }

    public static StdResult fail() {
        return fail("操作失败");
    }

    public static StdResult fail(String msg) {
        return new StdResult("400", msg, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
