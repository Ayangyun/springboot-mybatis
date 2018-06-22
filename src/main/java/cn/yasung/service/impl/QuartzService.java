package cn.yasung.service.impl;

import cn.yasung.mapper.IntegralExitMapper;
import cn.yasung.mapper.IntegralMapper;
import cn.yasung.mapper.PerformanceMapper;
import cn.yasung.model.Integral;
import cn.yasung.model.IntegralExit;
import cn.yasung.model.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class QuartzService {
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private IntegralExitMapper integralExitMapper;
    @Autowired
    private PerformanceMapper performanceMapper;

    //    每月1号凌晨12点启动
   // @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    @Scheduled(cron = "0 0 0 1 * ?")
    public void timerToNow() throws ParseException {

        Integral integral;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date = calendar.getTime();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);//0代表第一个月
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        List<Performance> performanceList = performanceMapper.getMonthPerformance(String.valueOf(month+1),year);
        String marketingName =performanceList.get(0).getMarketingName();//找到当月销售额最高的给他的月积分加2分
        integral = integralMapper.getIntegral(marketingName);
        integral.setMonthIntegral(integral.getMonthIntegral()+2);
        integral.setMonthChangeDate(new Date());
        integralMapper.updateIntegral(integral);
        //根据月积分最高的 年积分加一 最小的 减一分 ！！；
        List<Integral> integralList = integralMapper.getListIntegers();
        Integral integral1 =integralList.get(0);
        integral1.setYearIntegral(integral1.getYearIntegral()+1);//月积分最高加一分
        integral1.setYearChangeDate(new Date());
        integralMapper.updateIntegral(integral1);
        Integral integral2=integralList.get(integralList.size()-1);//月积分最低扣除一分
        integral2.setYearIntegral(integral1.getYearIntegral()-1);
        integral2.setYearChangeDate(new Date());
        integralMapper.updateIntegral(integral2);
        //查找最新积分排名并保存当月积分到另外表月积分归零
        List<Integral> integralList1 = integralMapper.getListIntegers();
        for (int i=0;i<integralList1.size();i++){
            IntegralExit integralExit = new IntegralExit();
            integralExit.setMarketingName(integralList1.get(i).getMarketingName());//数据转移到扩展表
            integralExit.setMonthIntegral(integralList1.get(i).getMonthIntegral());
            integralExit.setYearIntegral(integralList1.get(i).getYearIntegral());
            integralExit.setMonth(String.valueOf(month+1));
            integralExit.setRanking(String.valueOf(i+1));
            integralExit.setYear(year);
            integralExitMapper.addIntegralExit(integralExit);
            integralList1.get(i).setMonthIntegral(0);//把月积分清零
            integralMapper.updateIntegral(integralList1.get(i));
        }
        System.out.println("now time:" + new SimpleDateFormat("MM").format(new Date()));
    }

}