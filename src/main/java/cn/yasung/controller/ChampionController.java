package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.Champion;
import cn.yasung.model.Marketing;
import cn.yasung.service.ChampionService;
import cn.yasung.service.MarketingService;
import cn.yasung.utils.UploadQNImg;
import cn.yasung.vo.MarketingVo;
import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * Created by yang on 2018/6/20.
 */
@RestController
@Api(value = "/champion" ,description = "冠军视频相关相关")
@RequestMapping(value = "/champion" )
public class ChampionController {

    private Logger logger = Logger.getLogger(ChampionController.class);

    @Autowired
    private ChampionService championService;


    @ApiOperation(value = "上传冠军图片或视频")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public APIResponse uploadVideo(@RequestParam(value = "file") MultipartFile videoFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) videoFile.getInputStream();
        if (videoFile.isEmpty()) {
            return new APIResponse<>("文件不能为空");
        }
        // 获取文件名
        String fileName = videoFile.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        UploadQNImg uploadQNImg = new UploadQNImg();
        String filePath =uploadQNImg.upload(inputStream,fileName);
        logger.info("上传成功后的文件路径未：" + filePath );
        return new APIResponse<>(filePath);

    }


    @ApiOperation(value = "增加图片或视频信息")
    @RequestMapping(value = {"/addMarketing" }, method = RequestMethod.POST)
    public APIResponse addChampion(@RequestBody Champion champion  )  throws RuntimeException {
             championService.addChampion(champion);
            return new APIResponse<>(APIResponseCodeEnum.SYSTEM_ADD.getMsg());

    }





/*    @ApiOperation(value = "修改某个销售代表信息")
    @RequestMapping(value = {"/updateMarketing" }, method = RequestMethod.PUT)
    public APIResponse updateMarketing(@RequestBody  Marketing marketing) {
        marketingService.updateMarketing(marketing);
        return new APIResponse<>(APIResponseCodeEnum.SYSTEM_UPDATE.getMsg());

    }*/


}
