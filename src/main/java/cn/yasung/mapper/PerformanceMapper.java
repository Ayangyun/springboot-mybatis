package cn.yasung.mapper;

import cn.yasung.model.Integral;
import cn.yasung.model.Performance;
import cn.yasung.model.PerformanceExit;
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
    List<Performance> getPerformanceList(@Param("saleroomDate") Date saleroomDate);
    List<Performance> getMonthPerformanceList(@Param("month")String month,@Param("year")String year);

    Performance getPerformance(@Param("saleroomDate") Date saleroomDate);

    Performance getMonthPerformance(@Param("month") String month, @Param("year") String year,@Param("marketingName") String marketingName);

    Performance getDayPerformance(@Param("saleroomDate") Date saleroomDate,@Param("marketingName") String marketingName);

    }
