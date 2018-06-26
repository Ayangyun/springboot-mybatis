package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.IntegralMapper;
import cn.yasung.mapper.MarketingMapper;
import cn.yasung.mapper.PerformanceExitMapper;
import cn.yasung.mapper.PerformanceMapper;
import cn.yasung.model.Integral;
import cn.yasung.model.Marketing;
import cn.yasung.model.Performance;
import cn.yasung.model.PerformanceExit;
import cn.yasung.service.IntegralService;
import cn.yasung.service.PerformanceService;
import cn.yasung.vo.IntegralVo;
import cn.yasung.vo.PerformanceVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.yasung.constants.Constant.SYSTEM_PAGENUM;
import static cn.yasung.constants.Constant.SYS_LENGTH;

/**
 * Project Name:
 * 功能描述：
 *
 * @author Ayang@unmz.net
 * @version 1.0
 * @date 2018-6-15 13:34
 * @since JDK 1.8
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceMapper performanceMapper;

    @Autowired
    private MarketingMapper marketingMapper;

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private PerformanceExitMapper performanceExitMapper;

    private Logger logger = Logger.getLogger(PerformanceServiceImpl.class);
    @Override
    public PageInfo<Performance> getListPerformance(PerformanceVo performanceVo)throws WeChatAPIBizException {
        try {
            Integer pageSize = SYSTEM_PAGENUM;

            if (performanceVo.getPageNum()==null || performanceVo.getPageNum()==0 || performanceVo.getPageNum()<0){
                performanceVo.setPageNum(1);
            }
            PageHelper.startPage(performanceVo.getPageNum(), pageSize);
            List<Performance> performanceList= performanceMapper.getListPerformance(performanceVo);

            PageInfo<Performance> pageInfo = new PageInfo<Performance>(performanceList);

            return pageInfo;
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }

    @Override
    public void addPerformance(List<Performance> performanceList)throws WeChatAPIBizException {
        try {

            Integral integral = new Integral();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            List<Performance> performanceList1 = new ArrayList<>();
            if (performanceList.size() != 0){

                Date newDate = new Date();//当前时间
                for (Performance performance : performanceList) {
                    PerformanceExit performanceExit=new PerformanceExit();
                    performanceExit.setSaleroom(performance.getSaleroom());//真实销售数据保存！
                    Marketing marketing = marketingMapper.getMarketing(performance.getMarketingName());
                    Calendar bef = Calendar.getInstance();
                    Calendar aft = Calendar.getInstance();
                    Calendar calendar = Calendar.getInstance();
                    bef.setTime(marketing.getHireDate());
                    aft.setTime(newDate);
                    int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
                    int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
                    int monthCha = Math.abs(month + result);
                    if (monthCha <= 6) {
                        performance.setSaleroom(performance.getSaleroom().multiply(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else if (7 <= monthCha && monthCha < 10) {
                        performance.setSaleroom(performance.getSaleroom().multiply(new BigDecimal(1.4)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else if (10 <= monthCha && monthCha < 13) {
                         performance.setSaleroom(performance.getSaleroom().multiply(new BigDecimal(1.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else if (13 <= monthCha && monthCha < 16) {
                        performance.setSaleroom(performance.getSaleroom().multiply(new BigDecimal(1.2)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else if (16 <= monthCha && monthCha < 19) {
                        performance.setSaleroom(performance.getSaleroom().multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else if (19 <= monthCha) {
                        performance.setSaleroom(performance.getSaleroom().multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    logger.info(performance);
                    System.out.println("1");
                    calendar.setTime(newDate);
                    int newMonth = calendar.get(Calendar.MONTH);//0代表第一个月
                    performance.setMonth(String.valueOf(newMonth+1));//销售月份
                    String year = String.valueOf(calendar.get(Calendar.YEAR));
                    performance.setYear(year);//销售年份
                    if (performance.getSaleroomDate()==null){
                            performance.setSaleroomDate(formatter.parse(formatter.format(new Date())));
                    }
                    performanceList1.add(performance);
                   performanceExit.setMarketingName(performance.getMarketingName());//存入真实的数据,以便于作进度根据
                   performanceExit.setSaleroomDate(performance.getSaleroomDate());
                   performanceExit.setMonth(performance.getMonth());
                   performanceExit.setYear(performance.getYear());
                   performanceExitMapper.addPerformanceExit(performanceExit);

                 }
                System.out.println("2");
                logger.info(2);
                BigDecimal count = new BigDecimal(0);//记录总共的金钱数
                BigDecimal maxSaleroom = performanceList1.get(0).getSaleroom();//记录最大金额
                BigDecimal minSaleroom = performanceList1.get(0).getSaleroom();//记录最小金额
                Performance performance1 = new Performance();
                for (int i = 0; i < performanceList1.size(); i++) {
                    count = count.add(performanceList.get(i).getSaleroom());//总共金额
                    if (maxSaleroom.compareTo(performanceList1.get(i).getSaleroom()) == -1 || maxSaleroom.compareTo(performanceList1.get(i).getSaleroom()) == 0) {
                        maxSaleroom = performanceList1.get(i).getSaleroom();//最大值
                        performance1 = performanceList1.get(i);
                    }
                    if (minSaleroom.compareTo(performanceList1.get(i).getSaleroom()) == 1 || minSaleroom.compareTo(performanceList1.get(i).getSaleroom()) == 0) {
                        minSaleroom = performanceList1.get(i).getSaleroom();//最小值
                    }
                }
                BigDecimal a = maxSaleroom.divide(count, 4, BigDecimal.ROUND_HALF_UP);
                if (a.compareTo(new BigDecimal(0.65)) == 1) {
                    integral = integralMapper.getIntegral(performance1.getMarketingName());
                    integral.setMonthIntegral(integral.getMonthIntegral()+2);
                    integral.setDayChampion(integral.getDayChampion()+1);//日冠军次数加一
                    integral.setMonthChangeDate(new Date());
                    integral.setDayChampionDate(new Date());
                    integralMapper.updateIntegral(integral);
                } else {
                    integral = integralMapper.getIntegral(performance1.getMarketingName());
                    integral.setMonthIntegral(integral.getMonthIntegral()+1);
                    integral.setDayChampion(integral.getDayChampion()+1);
                    integral.setMonthChangeDate(new Date());
                    integral.setDayChampionDate(new Date());
                    integralMapper.updateIntegral(integral);
                }
                List<Performance> performanceList2 = new ArrayList<>();
                for (Performance performance2 : performanceList1) {
                    if (performance2.getSaleroom().compareTo(minSaleroom) == 0) {//查询是否有多个最低成绩的赋予标示
                        performance2.setReciprocal("0");
                        performanceList2.add(performance2);
                    }else {
                        performance2.setReciprocal("1");
                        performanceList2.add(performance2);
                    }
                    performanceMapper.addPerformance(performance2);//数值录入数据库
                }
                Calendar calendar = Calendar.getInstance();//时间转换
                calendar.setTime(newDate);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                String dateString = formatter.format(calendar.getTime());
                Date currentTime_2;
                    currentTime_2 = formatter.parse(dateString);
                List<Performance> performances;
                performances = performanceMapper.getPerformance(currentTime_2);
                if (performances.size()==0){//如果星期天则在往前找一天；放长假就再往前找
                    for (int i=0;i<=SYS_LENGTH;i++) {
                        calendar.setTime(currentTime_2);
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                        Date currentTime_3;
                        currentTime_3 = formatter.parse(formatter.format(calendar.getTime()));//获得时间
                        performances = performanceMapper.getPerformance(currentTime_3);
                        if(performances!=null)break;
                    }
                }
                logger.info(performances);
                for (Performance performance3 : performances){
                    for (Performance performance4 : performanceList2) {//查询是否连续两天是倒数第一
                        if (performance3.getMarketingName().equals(performance4.getMarketingName())&& performance3.getReciprocal().equals("0")) {//两天连续倒数第一扣一分
                            integral = integralMapper.getIntegral(performance4.getMarketingName());
                            integral.setMonthIntegral(integral.getMonthIntegral()-1);
                            integral.setMonthChangeDate(new Date());
                            integralMapper.updateIntegral(integral);
                        }else{
                            System.out.println("没有连续两天倒数第一");
                        }
                    }
                }

            } else {
                throw new WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }

        }catch (WeChatAPIBizException e){
            throw e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }


    }




}
