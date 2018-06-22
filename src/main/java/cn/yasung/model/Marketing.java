package cn.yasung.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */

public class Marketing {
    private Integer id;
    private String marketingName;
    private String station;
    private String branch;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ",  timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss " )
    private Date hireDate;
    private String headUrl;
    private String videoUrl;


    @Override

    public String toString() {

        return "Marketing{" +

                "id='" + id + '\'' +

                ", marketingName='" + marketingName + '\'' +

                ", station='" + station + '\'' +

                ", branch='" + branch + '\'' +

                ", hireDate='" + hireDate + '\'' +

                ", headUrl=" + headUrl +

                ", videoUrl=" + videoUrl +

                '}';
    }

    public Marketing() {
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


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="CET")
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }



}
