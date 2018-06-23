package cn.yasung.mapper;

import cn.yasung.model.IntegralExit;
import cn.yasung.model.Target;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ayang on 2018/6/19.
 */
public interface TargetMapper {

    void  addTarget(Target target);
    List<Target> getListTarget();
    Target getMonthTarget(@Param("month") String month, @Param("year") String year);
    Target getYearTarget(@Param("year") String year);


    }
