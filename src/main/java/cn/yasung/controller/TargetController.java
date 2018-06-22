package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.Integral;
import cn.yasung.model.Performance;
import cn.yasung.model.Target;
import cn.yasung.service.IntegralService;
import cn.yasung.service.TargetService;
import cn.yasung.vo.IntegralVo;
import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by yang on 2018/5/31.
 */
@RestController
@Api(value = "目标相关" ,description = "描述信息")
@RequestMapping(value = "/target")
public class TargetController {

    private Logger logger = Logger.getLogger(TargetController.class);

    @Autowired
    private TargetService targetService;


    @ApiOperation(value = "增加目标")
    @RequestMapping(value = {"/addTarget"},method = RequestMethod.POST)
    public APIResponse addTarget(@RequestBody Target target){
        targetService.addTarget(target);
        return new  APIResponse<>(APIResponseCodeEnum.SYSTEM_ADD.getMsg());

    }

    @ApiOperation(value = "获取目标列表")
    @RequestMapping(value = {"/getTargetList"},method = RequestMethod.POST)
    public APIResponse getListTarget(){
        List<Target> targetList=  targetService.getListTarget();
        return new  APIResponse<>(targetList);

    }





    }



