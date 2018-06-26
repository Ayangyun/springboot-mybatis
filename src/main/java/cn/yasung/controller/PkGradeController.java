package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.pojo.RequestPojo;
import cn.yasung.pojo.Schedule;
import cn.yasung.service.PkGradeService;
import cn.yasung.vo.ChampionVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by yang on 2018/5/31.
 */
@RestController

@Api(value = "/pk", description = "PK榜单")
@RequestMapping(value = "/pk" )
public class PkGradeController {

    private Logger logger = Logger.getLogger(PkGradeController.class);

    @Autowired
    private PkGradeService pkGradeService;



    @ApiOperation(value = "获取PK成绩")
    @RequestMapping(value = {"/getListGrade"},method = RequestMethod.GET)
    public APIResponse getListGrade(){

       List<RequestPojo> requestPojoList =pkGradeService.getListGrade();

        return new  APIResponse<>(requestPojoList);

    }

    @ApiOperation(value = "获取进度数值")
    @RequestMapping(value = {"/getSchedule"},method = RequestMethod.GET)
    public APIResponse<Schedule> getSchedule(){
       Schedule schedule =pkGradeService.getSchedule();
        return new  APIResponse<>(schedule);

    }

    @ApiOperation(value = "获取冠军视频")
    @RequestMapping(value = {"/getChampion"},method = RequestMethod.GET)
    public APIResponse <ChampionVo>getChampion(@RequestParam("identification")String identification){
        ChampionVo championVo = pkGradeService.getChampion(identification);
        return new  APIResponse<>(championVo);
    }


















}
