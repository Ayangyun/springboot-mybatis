package cn.yasung.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ayang on 2018/6/12.
 */

public class Performance {
    private Integer id;
    private String marketingName;
    private BigDecimal saleroom ;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ",  timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd" )
    private Date saleroomDate;

    private String reciprocal;//倒数第一表示位

    private String month;//销售金额月份

    private String year;//销售年份




    public Performance() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public BigDecimal getSaleroom() {
        return saleroom;
    }

    public void setSaleroom(BigDecimal saleroom) {
        this.saleroom = saleroom;
    }

    public Date getSaleroomDate() {
        return saleroomDate;
    }

    public void setSaleroomDate(Date saleroomDate) {
        this.saleroomDate = saleroomDate;
    }

    public String getReciprocal() {
        return reciprocal;
    }

    public void setReciprocal(String reciprocal) {
        this.reciprocal = reciprocal;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
