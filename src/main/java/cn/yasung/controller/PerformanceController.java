package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.Integral;
import cn.yasung.model.Performance;
import cn.yasung.service.IntegralService;
import cn.yasung.service.PerformanceService;
import cn.yasung.vo.IntegralVo;
import cn.yasung.vo.PerformanceVo;
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
 * Created by Ayang on 2018/7/18.
 */

@RestController("FrontendCommodityController")
@Api(value = "/performance" ,description = "销售额相关")
@RequestMapping(value = "/performance")
public class PerformanceController {

    private Logger logger = Logger.getLogger(PerformanceController.class);


    @Autowired
    private PerformanceService performanceService;


    @ApiOperation(value = "分页获取所有销售额")
    @RequestMapping(value = {"/getListPerformance"},method = RequestMethod.POST)
    public APIResponse getListPerformance(@RequestBody(required = false) PerformanceVo performanceVo){

        PageInfo<Performance> pageInfo =performanceService.getListPerformance(performanceVo);

        return new  APIResponse<>(pageInfo);

    }


    @ApiOperation(value = "录入销售额")
    @RequestMapping(value = {"/addPerformance"},method = RequestMethod.POST)
    public APIResponse addPerformance(@RequestBody List<Performance> performanceList){
         performanceService.addPerformance(performanceList);
        return new  APIResponse<>(APIResponseCodeEnum.SYSTEM_ADD.getMsg());

    }





    }



