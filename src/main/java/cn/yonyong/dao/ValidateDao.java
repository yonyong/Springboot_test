package cn.yonyong.dao;


import cn.yonyong.pojo.Validate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ValidateDao {

    //判断改手机号是否已经在数据库中
    public Validate queryYZM(String tel);

    //如果用户手机号不存在，将手机号、验证码，及获取验证码生成的时间存入数据库
    public  int insertYZM(@Param("tel") String tel, @Param("yzm") String yzm, @Param("sendValidateTime") Date sendValidateTime);

    //如果用户手机号已经存在，将手机号、验证码，及获取验证码生成的时间更新存入数据库
    public  int updateYZM(@Param("tel") String tel, @Param("yzm") String yzm, @Param("sendValidateTime") Date sendValidateTime);
}
