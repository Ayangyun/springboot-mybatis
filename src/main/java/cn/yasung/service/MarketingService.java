package cn.yasung.service;

import cn.yasung.model.Marketing;
import cn.yasung.model.User;
import cn.yasung.vo.MarketingVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2018/5/31.
 */

@Service
public interface MarketingService {
    Marketing getMarketing(Integer id);
    void addMarketing(Marketing marketing);
    void deleteMarketing(Integer id);
    void updateMarketing(Marketing marketing);
    PageInfo<Marketing> getPageMarketing(MarketingVo marketingVo);
    List<Marketing> getListMarketing();

}
