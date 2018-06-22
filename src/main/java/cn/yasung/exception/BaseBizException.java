package cn.yasung.exception;


import cn.yasung.constants.APIResponseCodeEnum;

/**
 * Project Name:
 * 功能描述：基础业务异常封装类
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/3/6 14:56
 * @since JDK 1.8
 */
public class BaseBizException extends RuntimeException {

    private static final long serialVersionUID = 8719332364846263891L;

    private Integer code;

    public BaseBizException() {
        this(APIResponseCodeEnum.FAIL.getCode());
    }

    public BaseBizException(Integer code) {
        this(code, APIResponseCodeEnum.fromValue(code));
    }

    public BaseBizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
