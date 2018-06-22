package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.Integral;
import cn.yasung.model.User;
import cn.yasung.service.IntegralService;
import cn.yasung.service.UserService;
import cn.yasung.utils.MD5Util;
import cn.yasung.vo.IntegralVo;
import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by yang on 2018/5/31.
 */
@RestController
@Api(value = "冠军相关" ,description = "描述信息")
@RequestMapping(value = "/integral")
public class IntegralController {

    private Logger logger = Logger.getLogger(IntegralController.class);

    @Autowired
    private IntegralService integralService;


    @ApiOperation(value = "获取冠军次数")
    @RequestMapping(value = {"/getListIntegral"},method = RequestMethod.POST)
    public APIResponse getListIntegral(@RequestBody(required = false) IntegralVo integralVo){

        PageInfo<Integral> pageInfo =integralService.getListIntegral(integralVo);

        return new  APIResponse<>(pageInfo);

    }





    }



