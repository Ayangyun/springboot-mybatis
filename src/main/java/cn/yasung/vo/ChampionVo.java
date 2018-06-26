package cn.yasung.vo;


import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by zl on 2015/8/27.
 */

public class ChampionVo {
    private Integer id;
    @ApiModelProperty(value = "url地址")
    private String url;
    @ApiModelProperty(value = "标识是月冠军还是年冠军，1是月冠军、2是年冠军")
    private String identification;
    @ApiModelProperty(value = "文字")
    private String  manifesto;
    @ApiModelProperty(value = "标识是图片还是视频 1 图片 2视频")
    private  String urlIdentification;



    public ChampionVo() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getManifesto() {
        return manifesto;
    }

    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }

    public String getUrlIdentification() {
        return urlIdentification;
    }

    public void setUrlIdentification(String urlIdentification) {
        this.urlIdentification = urlIdentification;
    }
}
