package cn.yasung.pojo;


import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class ReustPojo {
    @ApiModelProperty(value = "销售代表姓名")
    private String marketingName;//销售代表姓名
    @ApiModelProperty(value = "头像路径")
    private String headUrl;//头像路径
    @ApiModelProperty(value = "年积分")
    private Integer yearIntegral;//年积分
    @ApiModelProperty(value = "当月积分")
    private Integer atMonthIntegral;//当月积分
    @ApiModelProperty(value = "当天销售额")
    private BigDecimal atSaleroom;//当天销售额
    @ApiModelProperty(value = "当月销售额")
    private BigDecimal atMonthSaleroom;//当月销售额
    @ApiModelProperty(value = "上月销售额")
    private BigDecimal lastMonthSaleroom;//上月销售额
    @ApiModelProperty(value = "上月积分")
    private Integer lastMonthIntegral;//上月积分
    @ApiModelProperty(value = "上月排名")
    private String  lastRanking;//上月排名
    @ApiModelProperty(value = "当月排名")
    private String atRanking;//当月排名
    @ApiModelProperty(value = "当日销售最高的标识橙子")
    private String orange;//当日销售最高的标识橙子



    public ReustPojo() {
    }



    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public Integer getYearIntegral() {
        return yearIntegral;
    }

    public void setYearIntegral(Integer yearIntegral) {
        this.yearIntegral = yearIntegral;
    }

    public Integer getAtMonthIntegral() {
        return atMonthIntegral;
    }

    public void setAtMonthIntegral(Integer atMonthIntegral) {
        this.atMonthIntegral = atMonthIntegral;
    }

    public BigDecimal getAtSaleroom() {
        return atSaleroom;
    }

    public void setAtSaleroom(BigDecimal atSaleroom) {
        this.atSaleroom = atSaleroom;
    }

    public BigDecimal getAtMonthSaleroom() {
        return atMonthSaleroom;
    }

    public void setAtMonthSaleroom(BigDecimal atMonthSaleroom) {
        this.atMonthSaleroom = atMonthSaleroom;
    }

    public BigDecimal getLastMonthSaleroom() {
        return lastMonthSaleroom;
    }

    public void setLastMonthSaleroom(BigDecimal lastMonthSaleroom) {
        this.lastMonthSaleroom = lastMonthSaleroom;
    }

    public Integer getLastMonthIntegral() {
        return lastMonthIntegral;
    }

    public void setLastMonthIntegral(Integer lastMonthIntegral) {
        this.lastMonthIntegral = lastMonthIntegral;
    }



    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getLastRanking() {
        return lastRanking;
    }

    public void setLastRanking(String lastRanking) {
        this.lastRanking = lastRanking;
    }

    public String getAtRanking() {
        return atRanking;
    }

    public void setAtRanking(String atRanking) {
        this.atRanking = atRanking;
    }

    public String getOrange() {
        return orange;
    }

    public void setOrange(String orange) {
        this.orange = orange;
    }
}
