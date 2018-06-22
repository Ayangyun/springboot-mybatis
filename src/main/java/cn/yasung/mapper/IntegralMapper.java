package cn.yasung.mapper;

import cn.yasung.model.Integral;
import cn.yasung.model.User;
import cn.yasung.vo.IntegralVo;
import cn.yasung.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */
public interface IntegralMapper {

    List<Integral> getListDayInteger();
    List<Integral> getListMonthInteger();
    List<Integral> getListYearInteger();
    Integral getIntegral(String marketingName);
    void  updateIntegral(Integral integral);
    void  addIntegral(Integral integral);
    void deleteIntegral(String marketingName);
    List<Integral>getListIntegers();

    }
