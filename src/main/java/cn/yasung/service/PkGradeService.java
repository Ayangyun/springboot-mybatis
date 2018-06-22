package cn.yasung.service;

import cn.yasung.model.Target;
import cn.yasung.pojo.ReustPojo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface PkGradeService {


    List<ReustPojo> getListGrade();



}
