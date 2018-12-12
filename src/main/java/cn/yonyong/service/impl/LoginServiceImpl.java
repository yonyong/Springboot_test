package cn.yonyong.service.impl;

import cn.yonyong.dao.LoginDao;
import cn.yonyong.pojo.Login;
import cn.yonyong.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    //查询login表中是否已经有过该用户错误登陆记录
    @Override
    public Login queryUser(String tel) {
        return loginDao.queryUser(tel);
    }

    //如果该用户没有错误登陆记录，表中没有他的数据，则向表中添加
    @Override
    public int insertUser(String tel, Date sendValidateTime, int count) {
        return loginDao.insertUser(tel,sendValidateTime,count);
    }

    //更新用户的错误登陆时间
    @Override
    public int updateUser(String tel,Date
            sendValidateTime,int count) {
        return loginDao.updateUser(tel,sendValidateTime,count);
    }
}
