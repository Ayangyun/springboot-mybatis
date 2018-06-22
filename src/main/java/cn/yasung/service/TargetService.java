package cn.yasung.service;

import cn.yasung.model.Integral;
import cn.yasung.model.Target;
import cn.yasung.vo.IntegralVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface TargetService {

    void addTarget(Target target);
    List<Target> getListTarget();


}
