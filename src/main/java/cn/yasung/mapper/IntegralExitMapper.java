package cn.yasung.mapper;

import cn.yasung.model.Integral;
import cn.yasung.model.IntegralExit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ayang on 2018/6/14.
 */
public interface IntegralExitMapper {

    void  addIntegralExit(IntegralExit integralExit);
    IntegralExit  getIntegralExit(@Param("month") String month ,@Param("year") String year, @Param("marketingName")String marketingName);


    }
