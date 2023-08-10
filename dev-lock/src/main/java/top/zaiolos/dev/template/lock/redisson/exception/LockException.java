package top.zaiolos.dev.template.lock.redisson.exception;

import lombok.Data;
import top.zaiolos.dev.template.lock.redisson.enums.LockErrorEnum;

/**
 * @author zdk
 */
@Data
public class LockException extends RuntimeException{

    protected Integer errorCode;

    protected String errorMsg;

    public LockException() {
        super();
    }

    public LockException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public LockException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public LockException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public LockException(LockErrorEnum error) {
        super(error.getMsg());
        this.errorCode = error.getCode();
        this.errorMsg = error.getMsg();
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
