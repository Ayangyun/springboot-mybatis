package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.Champion;
import cn.yasung.model.Marketing;
import cn.yasung.model.Performance;
import cn.yasung.pojo.ReustPojo;
import cn.yasung.pojo.Schedule;
import cn.yasung.service.MarketingService;
import cn.yasung.service.PkGradeService;
import cn.yasung.utils.UploadQNImg;
import cn.yasung.vo.MarketingVo;
import cn.yasung.vo.PerformanceVo;
import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * Created by yang on 2018/5/31.
 */
@RestController
@Api(value = "pk榜单" ,description = "pk榜单")
@RequestMapping(value = "/pk" )
public class PkGradeController {

    private Logger logger = Logger.getLogger(PkGradeController.class);

    @Autowired
    private PkGradeService pkGradeService;



    @ApiOperation(value = "获取PK成绩")
    @RequestMapping(value = {"/getListGrade"},method = RequestMethod.GET)
    public APIResponse getListGrade(){

       List<ReustPojo> reustPojoList =pkGradeService.getListGrade();

        return new  APIResponse<>(reustPojoList);

    }

    @ApiOperation(value = "获取进度数值")
    @RequestMapping(value = {"/getSchedule"},method = RequestMethod.GET)
    public APIResponse getSchedule(){
       Schedule schedule =pkGradeService.getSchedule();
        return new  APIResponse<>(schedule);

    }

    @ApiOperation(value = "获取冠军视频")
    @RequestMapping(value = {"/getChampion"},method = RequestMethod.GET)
    public APIResponse getChampion(@RequestParam("identification")String identification){
        Champion champion = pkGradeService.getChampion(identification);
        return new  APIResponse<>(champion);

    }


















}
