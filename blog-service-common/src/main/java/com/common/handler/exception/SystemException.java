package com.common.handler.exception;

/**
 * 系统异常
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

public class SystemException extends RuntimeException {
    private int code;
    private String msg;

    public SystemException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SystemException)) {
            return false;
        } else {
            SystemException other = (SystemException)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    return other$msg == null;
                } else return this$msg.equals(other$msg);
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SystemException;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getCode();
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getCode();
        return "SystemException(code=" + var10000 + ", msg=" + this.getMsg() + ")";
    }
}
