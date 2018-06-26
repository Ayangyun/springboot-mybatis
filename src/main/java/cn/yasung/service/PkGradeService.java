package cn.yasung.service;

import cn.yasung.pojo.RequestPojo;
import cn.yasung.pojo.Schedule;
import cn.yasung.vo.ChampionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface PkGradeService {


    List<RequestPojo> getListGrade();
    Schedule getSchedule();
    ChampionVo getChampion(String identification);


}
