package cn.yasung.vo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class IntegralVo {

    private Integer pageNum;

    private String Champion;




    public IntegralVo() {
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getChampion() {
        return Champion;
    }

    public void setChampion(String champion) {
        Champion = champion;
    }
}
