package cn.yasung.constants;

import java.io.Serializable;

/**
 * API 接口返回结构
 * @param <T>
 */
public class APIResponse<T> implements Serializable{

    private static final long serialVersionUID = 697980425283824017L;
    private Integer code;
    private String msg;
    private T data;
    private Long timestamp;

    public APIResponse() {
        this.code = APIResponseCodeEnum.SUCCESS.getCode();
        this.msg = APIResponseCodeEnum.SUCCESS.getMsg();
        this.data = null;
        this.timestamp = System.currentTimeMillis();
    }

    public APIResponse(T data) {
        this.code = APIResponseCodeEnum.SUCCESS.getCode();
        this.msg = APIResponseCodeEnum.SUCCESS.getMsg();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public APIResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

//    @Override
//    public String toString() {
//        return JsonUtils.toJSON(this);
//    }
}
