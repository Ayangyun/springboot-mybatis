package cn.yasung.mapper;

import cn.yasung.model.IntegralExit;
import cn.yasung.model.Target;

import java.util.List;

/**
 * Created by Ayang on 2018/6/19.
 */
public interface TargetMapper {

    void  addTarget(Target target);
    List<Target> getListTarget();


    }
