package cn.yasung.service;

import cn.yasung.model.Integral;
import cn.yasung.model.User;
import cn.yasung.vo.IntegralVo;
import cn.yasung.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface IntegralService {

    PageInfo<Integral> getListIntegral(IntegralVo integralVo);



}
