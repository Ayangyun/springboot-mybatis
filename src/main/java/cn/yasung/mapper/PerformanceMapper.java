package cn.yasung.mapper;

import cn.yasung.model.Integral;
import cn.yasung.model.Performance;
import cn.yasung.vo.PerformanceVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */
public interface PerformanceMapper {

    List<Performance> getListPerformance(PerformanceVo performanceVo);
    void addPerformance(Performance performance);
    List<Performance> getPerformance(@Param("saleroomDate") Date saleroomDate);
    List<Performance> getMonthPerformance(@Param("month")String month,@Param("year")String year);


    }
