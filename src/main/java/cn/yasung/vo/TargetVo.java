package cn.yasung.vo;


import java.math.BigDecimal;

/**
 * Created by Ayang on 2018/6/12.
 */

public class TargetVo {

    private String month;//那个月
    private String year;//那年
    private String pageNum;//分页


    public TargetVo() {
    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}

