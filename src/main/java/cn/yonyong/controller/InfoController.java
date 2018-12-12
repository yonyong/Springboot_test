package cn.yonyong.controller;

import cn.yonyong.dao.InfoDao;
import cn.yonyong.pojo.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Info")
public class InfoController {

    @Autowired
    InfoDao infoDao;

    //消息界面，查询所有消息展示在页面上
    @RequestMapping("/toInfo")
    public String toinfo(Model model, @RequestParam(required = true,defaultValue = "1")Integer pageNo,
                         @RequestParam(required = false,defaultValue = "6")Integer pageSize){
        List<Info> list=infoDao.queryInfo();

//        //修改
//        PageHelper.startPage(pageNo,pageSize);
//        PageInfo<Info> page=new PageInfo<Info>(list);
//        model.addAttribute("page",page);

        //以上为修改内容
        model.addAttribute("list",list);
        System.out.println("查询所有信息并展示。。。。");
        return "info";
    }

    //查询一个消息，将该消息内容展示出到一个页面用于编辑
    @RequestMapping("Info")
    public String queryInfoById(@RequestParam("id") int temp_id, Model model){
//        int temp_id=Integer.valueOf(request.getParameter("temp_id"));
        Info info=infoDao.queryInfoById(temp_id);
        model.addAttribute("info",info);
        return "editInfo";
    }

    //跳转到添加消息界面
    @RequestMapping("/toAddInfo")
    public String toaddinfo(){return "addInfo";}

    //添加消息
    @RequestMapping("/addInfo")
    public String addinfo(String title,String content
            ,String date){
        int rs=infoDao.addInfo(title,content,date);
        System.out.println("add info");
        return "redirect:/Info/toInfo";
    }

    //跳转到编辑消息界面
    @RequestMapping("/toEditInfo")
    public String todeleteinfo(){
        return "editInfo";
    }

    //删除消息
    @RequestMapping("/deleteInfo")
    public  String  deleteInfo(@RequestParam("id")int id){
        int rs=infoDao.deleteInfo(id);
        return "redirect:/Info/toInfo";
    }

    //编辑消息
   @RequestMapping("/editInfo")
    public  String editInfo(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("date")
            String date, @RequestParam("content") String content){
        int rs=infoDao.editInfo(id,title,date,content);
        return "redirect:/Info/toInfo";
    }


}
