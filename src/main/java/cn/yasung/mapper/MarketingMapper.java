package cn.yasung.mapper;

import cn.yasung.model.Marketing;
import cn.yasung.model.User;
import cn.yasung.vo.MarketingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */
public interface MarketingMapper {
    Marketing findMarketing(@Param("id") Integer id);
    void addMarketing(Marketing marketing);
    void updateMarketing(Marketing marketing);
    void deleteUserInfo(@Param("id")Integer id);
    List<Marketing> getPageMarketing(MarketingVo marketingVo);
    List<Marketing> getListMarketing();
    Marketing getMarketing(String marketingName);



    }
