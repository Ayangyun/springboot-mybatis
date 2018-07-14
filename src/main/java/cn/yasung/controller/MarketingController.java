package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.Marketing;
import cn.yasung.model.User;
import cn.yasung.service.MarketingService;
import cn.yasung.service.UserService;
import cn.yasung.utils.UploadQNImg;
import cn.yasung.vo.MarketingVo;
import com.github.pagehelper.PageInfo;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * Created by yang on 2018/5/31.
 */
@RestController
@Api(value = "销售代表相关" ,description = "销售代表相关")
@RequestMapping(value = "/marketing" )
public class MarketingController {

    private Logger logger = Logger.getLogger(MarketingController.class);


    @Autowired
    private MarketingService marketingService;


    @Value("${path.upload.images}")
    private String uploadDir;
    @ApiOperation(value = "上传头像")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public APIResponse uploadImage(@RequestParam(value = "file") MultipartFile file) throws IOException {
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        if (file.isEmpty()) {
            return new APIResponse<>("文件不能为空");
        }
        // 获取文件名
        //test
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        UploadQNImg uploadQNImg = new UploadQNImg();
        String filePath =uploadQNImg.upload(inputStream,fileName);
        logger.info("上传成功后的文件路径为：" + filePath );
        return new APIResponse<>(filePath);

    }





    @ApiOperation(value = "分页获取所有销售代表信息")
    @RequestMapping(value = {"/getPageMarketing"},method = RequestMethod.POST)
    public APIResponse<PageInfo<Marketing>> getPageMarketing(@RequestBody(required = false) MarketingVo marketingVo ) {
       PageInfo<Marketing> marketingList = marketingService.getPageMarketing(marketingVo);
        System.out.println(marketingList);
        return new APIResponse<>(marketingList);
    }

    @ApiOperation(value = "获取所有销售代表信息")
    @RequestMapping(value = {"/getListMarketing"},method = RequestMethod.GET)
    public APIResponse getListMarketing() {
        List<Marketing> marketingList = marketingService.getListMarketing();
        return new APIResponse<>(marketingList);

    }



    @ApiOperation(value = "增加销售代表信息")
    @RequestMapping(value = {"/addMarketing" }, method = RequestMethod.POST)
    public APIResponse addMarketing(@RequestBody Marketing marketing  )  throws RuntimeException {
            marketingService.addMarketing(marketing);
            return new APIResponse<>(APIResponseCodeEnum.SYSTEM_ADD.getMsg());

    }


    @ApiOperation(value = "获取单个销售代表信息")
    @RequestMapping(value = {"/getMarketing"},method = RequestMethod.GET)
    public APIResponse getMarketing(@RequestParam("id") Integer id) {
        Marketing marketing = marketingService.getMarketing(id);
        return new APIResponse<>(marketing);


    }

    @ApiOperation(value = "删除某个销售代表信息")
    @RequestMapping(value = {"/deleteMarketing"},method = RequestMethod.DELETE)
    public APIResponse deleteMarketing(@RequestParam("id") Integer id) {

        marketingService.deleteMarketing(id);

        return new APIResponse<>(APIResponseCodeEnum.SYSTEM_DELETE.getMsg());


    }


    @ApiOperation(value = "修改某个销售代表信息")
    @RequestMapping(value = {"/updateMarketing" }, method = RequestMethod.PUT)
    public APIResponse updateMarketing(@RequestBody  Marketing marketing) {
        marketingService.updateMarketing(marketing);
        return new APIResponse<>(APIResponseCodeEnum.SYSTEM_UPDATE.getMsg());

    }


}
