package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.UserMapper;
import cn.yasung.model.User;
import cn.yasung.service.UserService;
import cn.yasung.utils.MD5Util;
import cn.yasung.utils.PageUtil;
import cn.yasung.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static cn.yasung.constants.Constant.SYSTEM_PAGENUM;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-6-15 13:34
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(Integer id)throws WeChatAPIBizException {
        try {
            User user = userMapper.findUserInfo(id);
            if (user == null) {
                throw new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_USER.getCode());
            } else {
                return user;
            }
        }catch (WeChatAPIBizException e){
            throw e;
        } catch (Exception e) {
            throw new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }

    @Override
    public void savUserInfo(User user)throws WeChatAPIBizException  {
        try {
            if (user!=null){
                user.setPassWord(MD5Util.encrypt(user.getPassWord()));
                userMapper.savUserInfo(user);
            }else {
                throw  new  WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }

    }

    @Override
    public void deleteUserInfo(Integer id)throws WeChatAPIBizException  {

        try {
            if (id==null){
                throw new WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }else {
                userMapper.deleteUserInfo(id);
            }
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }

    @Override
    public void updateUserInfo(User user)throws WeChatAPIBizException  {

        try {
            if (user ==null && user.getId()!=null){
                throw new WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }else {
                user.setPassWord(MD5Util.encrypt(user.getPassWord()));
                userMapper.updateUserInfo(user);
            }
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }

    @Override
    public User loginUserInfo(String userName, String passWord)throws WeChatAPIBizException  {

        try {
            if (userName.equals("")  && passWord.equals("")){
                throw new WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }else {
                return userMapper.loginUserInfo(userName,passWord);
            }
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }



    }

    @Override
    public  PageInfo<User> getListUser(UserVo userVo)throws WeChatAPIBizException  {

        try {
            Integer pageSize = SYSTEM_PAGENUM;

            if (userVo.getPageNum()==null || userVo.getPageNum()==0 || userVo.getPageNum()<0){
                userVo.setPageNum(1);
            }
            PageHelper.startPage(userVo.getPageNum(), pageSize);
            List<User> userList = userMapper.getListUser(userVo);
            PageInfo<User> pageInfo = new PageInfo<>(userList);

            return pageInfo;
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }


}
