package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.controller.PerformanceController;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.*;
import cn.yasung.model.*;
import cn.yasung.pojo.ReustPojo;
import cn.yasung.pojo.Schedule;
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
    @Autowired
    private TargetMapper targetMapper;

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
            PerformanceExit performanceExits = performanceExitMapper.getPerformanceExit(date);//获得当日销售额最高的那个人
            for (int i = 0; i < integralList.size(); i++) {
                ReustPojo reustPojo = new ReustPojo();
                if (performanceExits.getMarketingName().equals(integralList.get(i).getMarketingName())) {//给销售额最高的一个标识
                    reustPojo.setOrange("1");
                }
                reustPojo.setMarketingName(integralList.get(i).getMarketingName());
                reustPojo.setHeadUrl(marketingMapper.getMarketing(integralList.get(i).getMarketingName()).getHeadUrl());
                reustPojo.setAtSaleroom(performanceExitMapper.getDayPerformanceExit(date, integralList.get(i).getMarketingName()).getSaleroom());//当天业绩
                reustPojo.setAtMonthSaleroom(performanceExitMapper.getMonthPerformanceExit(String.valueOf(month + 1), year, integralList.get(i).getMarketingName()).getSaleroom());//当月成绩
                PerformanceExit performanceExit = performanceExitMapper.getMonthPerformanceExit(String.valueOf(month), year, integralList.get(i).getMarketingName());//上月成绩
                if (performanceExit == null) {
                    reustPojo.setLastMonthSaleroom(new BigDecimal(0));
                } else {
                    reustPojo.setLastMonthSaleroom(performanceExit.getSaleroom());
                }
                reustPojo.setAtMonthIntegral(integralList.get(i).getMonthIntegral());//获得当月积分
                reustPojo.setYearIntegral(integralList.get(i).getYearIntegral());//获得年积分
                IntegralExit integralExit = integralExitMapper.getIntegralExit(String.valueOf(month), year, integralList.get(i).getMarketingName());
                if (integralExit == null) {//获得上月积分,如果新来员工上月没有记录就为零，排名为--
                    reustPojo.setLastMonthIntegral(0);
                    reustPojo.setLastRanking("--");
                } else {
                    reustPojo.setLastMonthIntegral(integralExit.getMonthIntegral());
                    reustPojo.setLastRanking(integralExit.getRanking());
                }
                reustPojo.setAtRanking(String.valueOf(i + 1));
                pojos.add(reustPojo);
                logger.info("成功获得pk成绩");

            }
            logger.info(pojos);
            return pojos;
        } catch (ParseException p) {
            return null;
        } catch (WeChatAPIBizException e) {
            throw e;
        } catch (Exception e) {
            throw new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }

    @Override
    public Schedule getSchedule() throws WeChatAPIBizException {

        try {
            Schedule schedule = new Schedule();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int month = calendar.get(Calendar.MONTH);//0代表第一个月
            String year = String.valueOf(calendar.get(Calendar.YEAR));//那一年
            Target target = targetMapper.getMonthTarget(String.valueOf(month + 1), year);
            Target target1 = targetMapper.getYearTarget(year);
            PerformanceExit performanceExit = performanceExitMapper.getLatMonthPerformanceExit(String.valueOf(month + 1), year);
            PerformanceExit performanceExit1 = performanceExitMapper.getLatYearPerformanceExit(year);
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//当月有几天
            int lastYear = calendar.get(Calendar.DAY_OF_YEAR);//今天相对今年过去了几天
            int lastMonth = calendar.get(Calendar.DAY_OF_MONTH);//今天相对这个月过去了几天
            int days;//某年(year)的天数
            if(Integer.valueOf(year) % 4 == 0 && Integer.valueOf(year) % 100 != 0 ||Integer.valueOf(year) % 400 == 0){//闰年的判断规则
                days=366;
            }else{
                days=365;
            }
            schedule.setAtMonthTarget(performanceExit.getSaleroom().divide(target.getTarget(),4, BigDecimal.ROUND_HALF_UP));
            schedule.setAtYarTarget(performanceExit1.getSaleroom().divide(target1.getTarget(),4, BigDecimal.ROUND_HALF_UP));
            schedule.setAtMonthPredict(new BigDecimal(lastMonth).divide(new BigDecimal(maxDay),4, BigDecimal.ROUND_HALF_UP));
            schedule.setAtYearPredict(new BigDecimal(lastYear).divide(new BigDecimal(days),4, BigDecimal.ROUND_HALF_UP));

            return schedule;
        } catch (WeChatAPIBizException e) {
            throw e;
        } catch (Exception e) {
            throw new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }

    private int orderDate(Date date) {
        int dateSum = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        System.out.println(dateStr);
        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(5, 7));
        int day = Integer.valueOf(dateStr.substring(8, 10));
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dateSum += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dateSum += 30;
                    break;
                case 2:
                    if (((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0))
                        dateSum += 29;
                    else dateSum += 28;
            }
        }
        return dateSum = dateSum + day;
    }


}