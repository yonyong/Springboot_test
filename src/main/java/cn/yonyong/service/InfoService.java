package cn.yonyong.service;



import cn.yonyong.pojo.Info;

import java.util.ArrayList;

public interface InfoService {
    //查询所有信息
    public ArrayList<Info> queryInfo();

    //查询一个消息，将该消息内容展示出到一个页面用于编辑
    public Info queryInfoById(int temp_id);

    //编辑消息
    public  int editInfo(int id, String title, String date, String content);

    //删除消息
    public  int deleteInfo(int temp_id);

    //添加消息
    public  int addInfo(String title, String content, String date);
}
