package cn.yonyong.service.impl;

import cn.yonyong.dao.ValidateDao;
import cn.yonyong.pojo.Validate;
import cn.yonyong.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    ValidateDao validateDao;
    //判断改手机号是否已经在数据库中
    @Override
    public Validate queryYZM(String tel) {
        return validateDao.queryYZM(tel);
    }

    //如果用户手机号不存在，将手机号、验证码，及获取验证码生成的时间存入数据库
    @Override
    public int insertYZM(String tel, String yzm, Date sendValidateTime) {
        return validateDao.insertYZM(tel,yzm,sendValidateTime);
    }

    //如果用户手机号已经存在，将手机号、验证码，及获取验证码生成的时间更新存入数据库
    @Override
    public int updateYZM(String tel, String yzm, Date sendValidateTime) {
        return validateDao.updateYZM(tel,yzm,sendValidateTime);
    }
}
