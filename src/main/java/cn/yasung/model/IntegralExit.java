package cn.yasung.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zl on 2018/6/14.
 */

public class IntegralExit {
    private Integer id;
    private String marketingName;
    private Integer monthIntegral;
    private Integer yearIntegral;
    private String ranking;
    private String month;
    private String year;



    public IntegralExit() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getMonthIntegral() {
        return monthIntegral;
    }

    public void setMonthIntegral(Integer monthIntegral) {
        this.monthIntegral = monthIntegral;
    }

    public Integer getYearIntegral() {
        return yearIntegral;
    }

    public void setYearIntegral(Integer yearIntegral) {
        this.yearIntegral = yearIntegral;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
