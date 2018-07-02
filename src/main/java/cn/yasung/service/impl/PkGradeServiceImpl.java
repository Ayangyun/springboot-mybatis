package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.controller.PerformanceController;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.*;
import cn.yasung.model.*;
import cn.yasung.pojo.RequestPojo;


import cn.yasung.pojo.Schedule;
import cn.yasung.service.PkGradeService;
import cn.yasung.service.TargetService;
import cn.yasung.vo.ChampionVo;
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

import static cn.yasung.constants.Constant.SYS_LENGTH;
import static cn.yasung.constants.Constant.url;

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
    @Autowired
    private ChampionMapper championMapper;

    private Logger logger = Logger.getLogger(PkGradeServiceImpl.class);

    @Override
    public List<RequestPojo> getListGrade() throws WeChatAPIBizException {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int month = calendar.get(Calendar.MONTH);//0代表第一个月
            String year = String.valueOf(calendar.get(Calendar.YEAR));//获取年份
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date;
            date= formatter.parse(formatter.format(calendar.getTime()));
            List<RequestPojo> requestPojoList = new ArrayList<>();
            List<Integral> integralList = integralMapper.getListIntegers();
            Performance performance;
            performance = performanceMapper.getPerformance(date);//获得当日销售额最高的那个人
            if (performance==null){//没有数据往前找，直至找到数据。
                for (int i=0;i<=SYS_LENGTH;i++){
                    calendar.setTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                    date=formatter.parse(formatter.format(calendar.getTime()));
                    performance= performanceMapper.getPerformance(date);
                    if (performance!=null) break;
                }
            }
            for (int i = 0; i < integralList.size(); i++) {
                RequestPojo requestPojo = new RequestPojo();
                if (performance.getMarketingName().equals(integralList.get(i).getMarketingName())) {//给销售额最高的一个标识
                    requestPojo.setOrange("1");
                }
                requestPojo.setMarketingName(integralList.get(i).getMarketingName());
                requestPojo.setHeadUrl(marketingMapper.getMarketing(integralList.get(i).getMarketingName()).getHeadUrl());
                Performance performance1=performanceMapper.getDayPerformance(date, integralList.get(i).getMarketingName());
                if (performance1==null){
                    requestPojo.setAtSaleroom(new BigDecimal(0));//当天业绩
                }else {
                    requestPojo.setAtSaleroom(performance1.getSaleroom());//当天业绩
                }
                Performance performance2 =performanceMapper.getMonthPerformance(String.valueOf(month + 1), year, integralList.get(i).getMarketingName());
                if (performance2==null){
                    requestPojo.setAtMonthSaleroom(new BigDecimal(0));//当月成绩
                }else {
                    requestPojo.setAtMonthSaleroom(performance2.getSaleroom());//当月成绩
                }
                Performance performance3 = performanceMapper.getMonthPerformance(String.valueOf(month), year, integralList.get(i).getMarketingName());//上月成绩
                if (performance3 == null) {//上个月没成绩
                    requestPojo.setLastMonthSaleroom(new BigDecimal(0));
                } else {
                    requestPojo.setLastMonthSaleroom(performance3.getSaleroom());
                }
                requestPojo.setAtMonthIntegral(integralList.get(i).getMonthIntegral());//获得当月积分
                requestPojo.setYearIntegral(integralList.get(i).getYearIntegral());//获得年积分
                IntegralExit integralExit = integralExitMapper.getIntegralExit(String.valueOf(month), year, integralList.get(i).getMarketingName());
                if (integralExit == null) {//获得上月积分,如果新来员工上月没有记录就为零，排名为--
                    requestPojo.setLastMonthIntegral(0);
                    requestPojo.setLastRanking("--");
                } else {
                    requestPojo.setLastMonthIntegral(integralExit.getMonthIntegral());
                    requestPojo.setLastRanking(integralExit.getRanking());
                }
                requestPojo.setAtRanking(String.valueOf(i + 1));
                requestPojoList.add(requestPojo);
                logger.info("成功获得pk成绩");
            }
            return requestPojoList;
        } catch (ParseException p) {
            logger.error("时间转换问题");
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
            Target target = targetMapper.getMonthTarget(String.valueOf(month + 1), year);//月目标
            Target target1 = targetMapper.getYearTarget(year);//年目标
            PerformanceExit performanceExit = performanceExitMapper.getLatMonthPerformanceExit(String.valueOf(month + 1), year);//月销售额度
            PerformanceExit performanceExit1 = performanceExitMapper.getLatYearPerformanceExit(year);//年销售额度

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

    @Override
    public ChampionVo getChampion(String identification)throws WeChatAPIBizException {

        try {

           ChampionVo championVo = new ChampionVo();
           Champion champion= championMapper.getChampion(identification);
           String championUrl=champion.getUrl().substring(champion.getUrl().lastIndexOf(".")+1,champion.getUrl().length());
           if (championUrl.equals("jpg") || championUrl.equals("png")){
               championVo.setUrlIdentification("1");
           }else  if (championUrl.equals("mp4") || championUrl.equals("avi") || championUrl.equals("rm") || championUrl.equals("rmvb")){
               championVo.setUrlIdentification("2");
           }
           championVo.setUrl(champion.getUrl());
           championVo.setIdentification(champion.getIdentification());
           championVo.setManifesto(champion.getManifesto());

            return championVo;

        }catch (WeChatAPIBizException w){
            throw w;
        }catch (Exception e){
            throw new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }



}