package cn.yasung.controller;

import cn.yasung.constants.APIResponse;
import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.model.User;
import cn.yasung.service.UserService;
import cn.yasung.vo.UserVo;
import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by yang on 2018/5/31.
 */
@RestController
@Api(value = "用户相关" ,description = "描述信息")
@RequestMapping(value = "/user" )
public class UserInfoController {

    private Logger logger = Logger.getLogger(UserInfoController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页获取所有用户信息")
    @RequestMapping(value = {"/getListUser"},method = RequestMethod.POST)
    public APIResponse<PageInfo<User>> getListUser(@RequestBody(required = false) UserVo userVo ) {
        PageInfo<User> userList = userService.getListUser(userVo);
        System.out.println(userList);
        return new APIResponse<>(userList);

    }


    @ApiOperation(value = "获取单个用户信息")
    @RequestMapping(value = {"/getUserInfo"},method = RequestMethod.GET)
    public APIResponse getUserInfo(@RequestParam(value = "id") Integer id) {
        User user = userService.getUserInfo(id);
        return new APIResponse<>(user);


    }

    @ApiOperation(value = "增加用户信息")
    @RequestMapping(value = {"/addUserInfo" }, method = RequestMethod.POST)
    public APIResponse addUserInfo(@RequestBody(required = false)  User user) {

            userService.savUserInfo(user);

        return new APIResponse<>(APIResponseCodeEnum.SYSTEM_ADD.getMsg());

    }

    @ApiOperation(value = "修改某个用户信息")
    @RequestMapping(value = {"/updateUserInfo" }, method = RequestMethod.PUT)
    public APIResponse updateUserInfo(@RequestBody(required = false)  User user) {
        userService.updateUserInfo(user);
        return new APIResponse<>(APIResponseCodeEnum.SYSTEM_UPDATE.getMsg());

    }


    @ApiOperation(value = "删除某个用户信息")
    @RequestMapping(value = {"/deleteUserInfo"},method = RequestMethod.DELETE)
    public APIResponse deleteUserInfo(@RequestParam(value = "id" ,required = true) Integer id) {

         userService.deleteUserInfo(id);

        return new APIResponse<>(APIResponseCodeEnum.SYSTEM_DELETE.getMsg());


    }


}
