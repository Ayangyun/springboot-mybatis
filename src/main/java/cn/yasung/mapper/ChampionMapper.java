package cn.yasung.mapper;

import cn.yasung.model.Champion;
import cn.yasung.model.Marketing;
import cn.yasung.vo.MarketingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yang on 2018/6/20.
 */
public interface ChampionMapper {
    Champion findChampion();
    void addChampion(Champion champion);
    void updateChampion(Champion champion);




    }
