package cn.yasung.constants;

/**
 * Project Name:
 * 功能描述：REST API接口状态码
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date Created by yang on 2018/5/31.
 * @since JDK 1.8
 */
public enum APIResponseCodeEnum {

    SUCCESS(10000, "success"),
    ERROR(20000, "success"),
    FAIL(-1,"fail"),

    SYSTEM_ERROR(99999, "系统错误"),
    AUTH_ERROR(99998, "没有访问权限"),
    REQUIRED_PARAM_EMPTY(99997, "必填参数为空"),

    SYSTEM_LOGIN(99996, "账号密码错误"),

    SYSTEM_LOGIN_TS(99995, "登录成功"),

    SYSTEM_USER(99994, "获取用户信息为空"),

    SYSTEM_DELETE(99993,"删除成功"),
    SYSTEM_ADD(99992,"新增成功"),
    SYSTEM_UPDATE(99991,"修改成功"),


    UNSUPPORTED_REQUEST(90000,"不支持的请求方式"),









    ;


    private Integer code;
    private String msg;

    APIResponseCodeEnum(Integer code, String msg) {
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

    public static String fromValue(int value) {
        for (APIResponseCodeEnum e : values()) {
            if (e.code == value) {
                return e.getMsg();
            }
        }
        return null;
    }
}
