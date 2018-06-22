package cn.yasung.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class Integral {
    private Integer id;
    private String marketingName;
    private Integer monthIntegral;
    private Integer yearIntegral;
    private Integer dayChampion;
    private Integer monthChampion;
    private Integer yearChampion;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ",  timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd " )
    private Date   monthChangeDate;

    private Date  yearChangeDate;

    private Date dayChampionDate;

    private Date monthChampionDate;

    private Date yearChampionDate;



    public Integral() {
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

    public Integer getDayChampion() {
        return dayChampion;
    }

    public void setDayChampion(Integer dayChampion) {
        this.dayChampion = dayChampion;
    }

    public Integer getMonthChampion() {
        return monthChampion;
    }

    public void setMonthChampion(Integer monthChampion) {
        this.monthChampion = monthChampion;
    }

    public Integer getYearChampion() {
        return yearChampion;
    }

    public void setYearChampion(Integer yearChampion) {
        this.yearChampion = yearChampion;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public Date getDayChampionDate() {
        return dayChampionDate;
    }

    public void setDayChampionDate(Date dayChampionDate) {
        this.dayChampionDate = dayChampionDate;
    }

    public Date getMonthChampionDate() {
        return monthChampionDate;
    }

    public void setMonthChampionDate(Date monthChampionDate) {
        this.monthChampionDate = monthChampionDate;
    }

    public Date getYearChampionDate() {
        return yearChampionDate;
    }

    public void setYearChampionDate(Date yearChampionDate) {
        this.yearChampionDate = yearChampionDate;
    }

    public Date getMonthChangeDate() {
        return monthChangeDate;
    }

    public void setMonthChangeDate(Date monthChangeDate) {
        this.monthChangeDate = monthChangeDate;
    }

    public Date getYearChangeDate() {
        return yearChangeDate;
    }

    public void setYearChangeDate(Date yearChangeDate) {
        this.yearChangeDate = yearChangeDate;
    }
}
