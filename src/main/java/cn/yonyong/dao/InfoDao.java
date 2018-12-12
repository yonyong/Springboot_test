package cn.yonyong.dao;

import cn.yonyong.pojo.Info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InfoDao {
    //查询所有信息
    public ArrayList<Info> queryInfo();

    //查询一个消息，将该消息内容展示出到一个页面用于编辑
    public Info queryInfoById(int temp_id);

    //编辑消息
    public  int editInfo(@Param("id") int id, @Param("title") String title, @Param("date") String date, @Param("content") String content);

    //删除消息
    public  int deleteInfo(int temp_id);

    //添加消息
    public  int addInfo(@Param("title") String title, @Param("content") String content, @Param("date") String date);
}
