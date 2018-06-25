package cn.yasung.vo;


import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class MarketingVo implements Serializable {

    private static final long serialVersionUID = -8164045367092534284L;

    @ApiModelProperty(value = "销售姓名")
    private String marketingName;//查询字段
    @ApiModelProperty(value = "职位")
    private String station;
    @ApiModelProperty(value = "部门")
    private String branch;
    @ApiModelProperty(value = "第几页")
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
