package cn.yasung.model;


import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ayang on 2018/6/12.
 */

public class Performance implements Serializable {
    private static final long serialVersionUID = 8563383625005774105L;
    @ApiModelProperty(value = "编号")
    private Integer id;
    @ApiModelProperty(value = "销售员姓名")
    private String marketingName;
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleroom ;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ",  timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd" )
    @ApiModelProperty(value = "销售时间")
    private Date saleroomDate;
    @ApiModelProperty(value = "倒数第一表示位")
    private String reciprocal;//倒数第一表示位
    @ApiModelProperty(value = "销售金额月份")
    private String month;//销售金额月份
    @ApiModelProperty(value = "销售年份")
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
