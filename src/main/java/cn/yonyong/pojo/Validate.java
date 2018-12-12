package cn.yonyong.pojo;

import java.util.Date;

public class Validate {
    private String tel;
    private String yzm;
    private Date time;
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getValidate() {
        return yzm;
    }
    public void setValidate(String validate) {
        this.yzm = validate;
    }
    public Date getDate() {
        return time;
    }
    public void setDate(Date time) {
        this.time = time;
    }
}