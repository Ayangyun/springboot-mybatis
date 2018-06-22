package cn.yasung.pojo;


import java.math.BigDecimal;

/**
 * Created by zl on 2015/8/27.
 */

public class Schedule {


    private BigDecimal atMonthTarget;//当月完成额百分比
    private BigDecimal atMonthPredict;//当月目标额度百分比
    private BigDecimal lastMonthTarget;//当年完成额度百分比
    private BigDecimal lastMonthPredict;//当年目标额度百分比



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

    public BigDecimal getLastMonthTarget() {
        return lastMonthTarget;
    }

    public void setLastMonthTarget(BigDecimal lastMonthTarget) {
        this.lastMonthTarget = lastMonthTarget;
    }

    public BigDecimal getLastMonthPredict() {
        return lastMonthPredict;
    }

    public void setLastMonthPredict(BigDecimal lastMonthPredict) {
        this.lastMonthPredict = lastMonthPredict;
    }
}
