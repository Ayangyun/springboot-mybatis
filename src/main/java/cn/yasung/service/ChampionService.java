package cn.yasung.service;

import cn.yasung.model.Champion;
import cn.yasung.model.Marketing;
import cn.yasung.vo.MarketingVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/6/20.
 */

@Service
public interface ChampionService {
        Champion getMarketing();
    void addChampion(Champion champion);

    void updateChampion(Champion champion);


}
