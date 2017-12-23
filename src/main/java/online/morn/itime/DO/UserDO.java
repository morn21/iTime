package online.morn.itime.DO;


import java.util.Date;

/**
 * 用户表
 * @auther Horner 2017/12/11 23:26
 */
public class UserDO {
    private String id;
    private Date createTime;
    private Date updateTime;
    private String name;
    private String password;
    private Integer isIng;
    private Date ingStartTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsIng() {
        return isIng;
    }

    public void setIsIng(Integer isIng) {
        this.isIng = isIng;
    }

    public Date getIngStartTime() {
        return ingStartTime;
    }

    public void setIngStartTime(Date ingStartTime) {
        this.ingStartTime = ingStartTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
