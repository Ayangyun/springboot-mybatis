package cn.yasung.vo;




/**
 * Created by zl on 2015/8/27.
 */

public class UserVo {

    private String userName;
    private String branch;
    private String nickName;
    private Integer pageNum;

    public UserVo() {
    }




    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
