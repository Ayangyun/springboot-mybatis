package cn.yasung.mapper;

import cn.yasung.model.User;
import cn.yasung.utils.PageUtil;
import cn.yasung.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2018/5/31.
 */
public interface UserMapper   {
    User findUserInfo(@Param("id")Integer id);
    void savUserInfo(User user);
    void updateUserInfo(User user);
    void deleteUserInfo(Integer id);
    User loginUserInfo(@Param("userName")String userName, @Param("passWord")String passWord );
    List<User> getListUser(UserVo userVo);
    Integer getCountUser();


    }
