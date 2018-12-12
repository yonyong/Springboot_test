package cn.yonyong.service;



import cn.yonyong.pojo.Login;

import java.util.Date;

public interface LoginService {
    //查询login表中是否已经有过该用户错误登陆记录
    public Login queryUser(String tel) ;

    //如果该用户没有错误登陆记录，表中没有他的数据，则向表中添加
    public  int insertUser(String tel, Date sendValidateTime, int count);

    //更新用户的错误登陆时间
    public  int updateUser(String tel, Date sendValidateTime, int count);
}
