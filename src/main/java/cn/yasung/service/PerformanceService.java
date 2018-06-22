package cn.yasung.service;

import cn.yasung.model.Integral;
import cn.yasung.model.Performance;
import cn.yasung.vo.IntegralVo;
import cn.yasung.vo.PerformanceVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface PerformanceService {

    PageInfo<Performance> getListPerformance(PerformanceVo performanceVo);
    void  addPerformance(List<Performance> performanceList);

}
