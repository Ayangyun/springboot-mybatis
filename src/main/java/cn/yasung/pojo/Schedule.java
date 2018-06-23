package cn.yasung.pojo;


import java.math.BigDecimal;

/**
 * Created by zl on 2015/8/27.
 */

public class Schedule {


    private BigDecimal atMonthTarget;//当月完成额百分比
    private BigDecimal atMonthPredict;//当月预计完成额度百分比
    private BigDecimal atYarTarget;//当年完成额度百分比
    private BigDecimal atYearPredict;//当年预计完成额度百分比



    public Schedule() {
    }


    public BigDecimal getAtMonthTarget() {
        return atMonthTarget;
    }

    public void setAtMonthTarget(BigDecimal atMonthTarget) {
        this.atMonthTarget = atMonthTarget;
    }

    public BigDecimal getAtMonthPredict() {
        return atMonthPredict;
    }

    public void setAtMonthPredict(BigDecimal atMonthPredict) {
        this.atMonthPredict = atMonthPredict;
    }


    public BigDecimal getAtYarTarget() {
        return atYarTarget;
    }

    public void setAtYarTarget(BigDecimal atYarTarget) {
        this.atYarTarget = atYarTarget;
    }

    public BigDecimal getAtYearPredict() {
        return atYearPredict;
    }

    public void setAtYearPredict(BigDecimal atYearPredict) {
        this.atYearPredict = atYearPredict;
    }
}
