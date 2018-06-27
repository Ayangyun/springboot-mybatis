package cn.yasung.model;


import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ayang on 2018/6/12.
 */

public class Target {
    private Integer id;
    @ApiModelProperty(value = "目标多少")
    private BigDecimal target;//目标
    @ApiModelProperty(value = "是哪个月的")
    private String month;//那个月
    @ApiModelProperty(value = "年份多少")
    private String year;//那年


    public Target() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }
}

