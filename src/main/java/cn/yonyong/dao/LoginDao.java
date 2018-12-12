package cn.yonyong.dao;

import cn.yonyong.pojo.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface LoginDao {

    //查询login表中是否已经有过该用户错误登陆记录
    public Login queryUser(String tel) ;

    //如果该用户没有错误登陆记录，表中没有他的数据，则向表中添加
    public  int insertUser(@Param("tel") String tel, @Param("sendValidateTime") Date sendValidateTime, @Param("count") int count);

    //更新用户的错误登陆时间
    public  int updateUser(@Param("tel") String tel, @Param("sendValidateTime") Date sendValidateTime, @Param("count") int count);
}
