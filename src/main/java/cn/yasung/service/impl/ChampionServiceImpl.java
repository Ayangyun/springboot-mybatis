package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.controller.PerformanceController;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.ChampionMapper;
import cn.yasung.mapper.IntegralMapper;
import cn.yasung.mapper.MarketingMapper;
import cn.yasung.model.Champion;
import cn.yasung.model.Integral;
import cn.yasung.model.Marketing;
import cn.yasung.service.ChampionService;
import cn.yasung.service.MarketingService;
import cn.yasung.vo.MarketingVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static cn.yasung.constants.Constant.SYSTEM_PAGENUM;

/**
 * Project Name:
 * 功能描述：
 *
 * @author Ayang
 * @version 1.0
 * @date 2018-6-15 13:34
 * @since JDK 1.8
 */
@Service
public class ChampionServiceImpl implements ChampionService {


    private Logger logger = Logger.getLogger(ChampionServiceImpl.class);

    @Autowired
    private ChampionMapper championMapper;


    @Override
    public void addChampion(Champion champion)throws WeChatAPIBizException  {

        try {
            if (champion!=null) {
                championMapper.updateChampion(champion);

            }else {
                throw  new  WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }

    }

    @Override
    public void updateChampion(Champion champion) throws WeChatAPIBizException  {

        try {

        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }




    @Override
    public Champion getMarketing() {
        return null;
    }



}
