package cn.yasung.service;

import cn.yasung.model.Champion;
import cn.yasung.model.Target;
import cn.yasung.pojo.ReustPojo;
import cn.yasung.pojo.Schedule;
import cn.yasung.vo.ChampionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface PkGradeService {


    List<ReustPojo> getListGrade();
    Schedule getSchedule();
    ChampionVo getChampion(String identification);


}
