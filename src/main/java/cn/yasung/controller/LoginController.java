package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.User;
import cn.yasung.service.UserService;
import cn.yasung.utils.MD5Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by yang on 2018/5/31.
 */
@RestController
@Api(value = "登录相关" ,description = "描述信息")
@RequestMapping(value = "/login")
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;




    @ApiOperation(value = "登录入口")
    @RequestMapping(value = {"/loginUserInfo"},method = RequestMethod.POST)
    public APIResponse loginUserInfo(@RequestParam(value = "userName") String userName , @RequestParam(value = "passWord")String passWord
    ){

        User user = userService.loginUserInfo(userName, MD5Util.encrypt(passWord));
            logger.info(user);
        if(user!=null){
            return new APIResponse<>(APIResponseCodeEnum.SYSTEM_LOGIN_TS.getMsg());
        }else{
        logger.error(APIResponseCodeEnum.SYSTEM_LOGIN.getCode());
        return new  APIResponse<>(APIResponseCodeEnum.SYSTEM_LOGIN.getMsg() );
            }

    }


}
