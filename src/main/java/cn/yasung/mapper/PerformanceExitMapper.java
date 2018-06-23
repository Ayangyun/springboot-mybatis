package cn.yasung.mapper;

import cn.yasung.model.Performance;
import cn.yasung.model.PerformanceExit;
import cn.yasung.pojo.Schedule;
import cn.yasung.vo.PerformanceVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */
public interface PerformanceExitMapper {

    void addPerformanceExit(PerformanceExit performanceExit);

    PerformanceExit getMonthPerformanceExit(@Param("month") String month, @Param("year") String year,@Param("marketingName") String marketingName);

    PerformanceExit getDayPerformanceExit(@Param("saleroomDate") Date saleroomDate,@Param("marketingName") String marketingName);

    PerformanceExit getPerformanceExit(@Param("saleroomDate") Date saleroomDate);

    PerformanceExit getLatMonthPerformanceExit(@Param("month") String month, @Param("year") String year);
    PerformanceExit getLatYearPerformanceExit( @Param("year") String year);


}
