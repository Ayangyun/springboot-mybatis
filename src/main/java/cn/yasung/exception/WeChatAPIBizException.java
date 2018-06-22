package cn.yasung.exception;

/**
 * Project Name:
 * 功能描述：微信公众号异常封装类
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/3/6 14:55
 * @since JDK 1.8
 */
public class WeChatAPIBizException extends BaseBizException {

    private static final long serialVersionUID = 7308593327743120940L;

    public WeChatAPIBizException() {
        super();
    }

    public WeChatAPIBizException(Integer code) {
        super(code);
    }

    public WeChatAPIBizException(Integer code, String message) {
        super(code, message);
    }
}
