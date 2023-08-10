package top.zaiolos.dev.template.lock.redisson.enums;

/**
 * @author zdk
 */

public enum LockErrorEnum {

    /**
     * 超时
     */
    Limit(9001, "请求太频繁,请稍后再试");

    private Integer code;

    private String msg;

    LockErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
