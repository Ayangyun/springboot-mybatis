package cn.yasung.vo;


import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class PerformanceVo {

    private String marketingName;
    private Date saleroomDate;
    private Integer pageNum;

    public PerformanceVo() {
    }



    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public Date getSaleroomDate() {
        return saleroomDate;
    }

    public void setSaleroomDate(Date saleroomDate) {
        this.saleroomDate = saleroomDate;
    }
}
