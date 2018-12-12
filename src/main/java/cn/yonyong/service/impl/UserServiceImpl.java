package cn.yonyong.service.impl;

import cn.yonyong.dao.UserDao;
import cn.yonyong.pojo.User;
import cn.yonyong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User queryUser(User user) {
        return userDao.queryUser(user);
    }

    @Override
    public User registerQueryUser(String tel) {
        return userDao.registerQueryUser(tel);
    }

    @Override
    public int registerUser(String tel,String password) {
        return userDao.registerUser(tel,password);
    }
}
