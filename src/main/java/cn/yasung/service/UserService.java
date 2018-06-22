package cn.yasung.service;

import cn.yasung.model.User;
import cn.yasung.utils.PageUtil;
import cn.yasung.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface UserService  {
    User getUserInfo(Integer id);
    void savUserInfo(User user);
    void deleteUserInfo(Integer id);
    void updateUserInfo(User user);
    User loginUserInfo(String userName , String passWord);
    PageInfo<User> getListUser (UserVo userVo);

}
