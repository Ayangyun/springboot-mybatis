package cn.yasung.vo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class MarketingVo {

    private String marketingName;//查询字段
    private String station;
    private String branch;
    private Integer pageNum;//第几页




    public MarketingVo() {
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
