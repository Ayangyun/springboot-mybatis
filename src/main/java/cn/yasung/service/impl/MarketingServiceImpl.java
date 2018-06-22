package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.IntegralMapper;
import cn.yasung.mapper.MarketingMapper;
import cn.yasung.mapper.UserMapper;
import cn.yasung.model.Integral;
import cn.yasung.model.Marketing;
import cn.yasung.model.User;
import cn.yasung.service.MarketingService;
import cn.yasung.service.UserService;
import cn.yasung.utils.MD5Util;
import cn.yasung.vo.MarketingVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

import static cn.yasung.constants.Constant.SYSTEM_PAGENUM;

/**
 * Project Name:
 * 功能描述：
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-6-15 13:34
 * @since JDK 1.8
 */
@Service
public class MarketingServiceImpl implements MarketingService {

    @Autowired
    private MarketingMapper marketingMapper;
    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public Marketing getMarketing(Integer id) throws WeChatAPIBizException  {
        try {
            if (id!=null) {
                return marketingMapper.findMarketing(id);
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
    public void addMarketing(Marketing marketing)throws WeChatAPIBizException  {

        try {
            if (marketing!=null) {
                Date date = new Date();
                marketingMapper.addMarketing(marketing);//新添加销售人员
                Integral integral = new Integral();//新增销售代表积分信息
                integral.setMarketingName(marketing.getMarketingName());
                integral.setMonthIntegral(0);
                integral.setYearIntegral(0);
                integral.setMonthChampion(0);
                integral.setDayChampion(0);
                integral.setYearChampion(0);
                integral.setMonthChangeDate(date);
                integral.setYearChangeDate(date);
                integral.setDayChampionDate(date);
                integral.setMonthChampionDate(date);
                integral.setYearChampionDate(date);
                integralMapper.addIntegral(integral);

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
    public void deleteMarketing(Integer id)throws WeChatAPIBizException  {

        try {
            if (id!=null) {
                Marketing marketing = marketingMapper.findMarketing(id);
                marketingMapper.deleteUserInfo(id);//顺带一起删除积分表信息
                integralMapper.deleteIntegral(marketing.getMarketingName());//一起删除

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
    public void updateMarketing(Marketing marketing)throws WeChatAPIBizException  {

        try {
            if(marketing!=null&&marketing.getId()!=null){
                marketingMapper.updateMarketing(marketing);
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
    public PageInfo<Marketing> getPageMarketing(MarketingVo marketingVo) {

        try {
            Integer pageSize = SYSTEM_PAGENUM;

            if (marketingVo.getPageNum()==null || marketingVo.getPageNum()==0 ||marketingVo.getPageNum()<0){
                marketingVo.setPageNum(1);
            }
            PageHelper.startPage(marketingVo.getPageNum(), pageSize);
            List<Marketing> marketingList = marketingMapper.getPageMarketing(marketingVo);
            PageInfo<Marketing> pageInfo = new PageInfo<>(marketingList);
            return pageInfo;
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }

    @Override
    public List<Marketing> getListMarketing() {
        return  marketingMapper.getListMarketing();
    }


}
