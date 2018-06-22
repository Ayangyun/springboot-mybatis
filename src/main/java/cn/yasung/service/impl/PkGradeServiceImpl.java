package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.*;
import cn.yasung.model.*;
import cn.yasung.pojo.ReustPojo;
import cn.yasung.service.PkGradeService;
import cn.yasung.service.TargetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Project Name:
 * 功能描述：
 *
 * @author Ayang@unmz.net
 * @version 1.0
 * @date 2018-6-21 14:34
 * @since JDK 1.8
 */
@Service
public class PkGradeServiceImpl implements PkGradeService {

    @Autowired
    private PerformanceMapper performanceMapper;
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private IntegralExitMapper integralExitMapper;
    @Autowired
    private PerformanceExitMapper performanceExitMapper;
    @Autowired
    private MarketingMapper marketingMapper;

    private Logger logger = Logger.getLogger(PkGradeServiceImpl.class);

    @Override
    public List<ReustPojo> getListGrade() throws WeChatAPIBizException {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int month = calendar.get(Calendar.MONTH);//0代表第一个月
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date = formatter.parse(formatter.format(calendar.getTime()));
            List<ReustPojo> pojos = new ArrayList<>();
            List<Integral> integralList = integralMapper.getListIntegers();
           // List<Marketing> marketingList = marketingMapper.getListMarketing();
            for (int i = 0; i < integralList.size(); i++) {
                ReustPojo reustPojo = new ReustPojo();
                reustPojo.setMarketingName(integralList.get(i).getMarketingName());
                reustPojo.setHeadUrl(marketingMapper.getMarketing(integralList.get(i).getMarketingName()).getHeadUrl());
                reustPojo.setAtSaleroom(performanceExitMapper.getDayPerformanceExit(date,integralList.get(i).getMarketingName()).getSaleroom());//当天业绩
                reustPojo.setAtMonthSaleroom(performanceExitMapper.getMonthPerformanceExit(String.valueOf(month+1),year,integralList.get(i).getMarketingName()).getSaleroom());//当月成绩
                PerformanceExit performanceExit=performanceExitMapper.getMonthPerformanceExit(String.valueOf(month),year,integralList.get(i).getMarketingName());//上月成绩
                if (performanceExit==null){
                    reustPojo.setLastMonthSaleroom(new BigDecimal(0));
                }else {
                    reustPojo.setLastMonthSaleroom(performanceExit.getSaleroom());
                }
                reustPojo.setAtMonthIntegral(integralList.get(i).getMonthIntegral());//获得当月积分
                reustPojo.setYearIntegral(integralList.get(i).getYearIntegral());//获得年积分
                IntegralExit integralExit =integralExitMapper.getIntegralExit(String.valueOf(month),year,integralList.get(i).getMarketingName());
                if (integralExit==null){//获得上月积分,如果新来员工上月没有记录就为零
                    reustPojo.setLastMonthIntegral(0);
                    reustPojo.setLastRanking("-");
                }else {
                    reustPojo.setLastMonthIntegral(integralExit.getMonthIntegral());
                    reustPojo.setLastRanking(integralExit.getRanking());
                }
                reustPojo.setAtRanking(String.valueOf(i+1));
                pojos.add(reustPojo);
                System.out.println("1");
                logger.info("成功获得pk成绩");

            }

            return pojos;
        } catch (ParseException p) {
               return null;
        } catch (WeChatAPIBizException e) {
            throw e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }

}