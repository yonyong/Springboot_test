package cn.yonyong.controller;

import cn.yonyong.pojo.Validate;
import cn.yonyong.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@Controller
public class ValidateController {

    @Autowired
    ValidateService validateService;
    //手机验证码模块

    //生成手机验证码
    @RequestMapping("/telValidateUtil")
    @ResponseBody
    public String TelValidate(@RequestParam(value = "tel")String tel){
        System.out.println("-----------------------------------------------");

        int intFlag = (int)(Math.random() * 1000000);

        String flag = String.valueOf(intFlag);
        if (flag.length() == 6 && flag.substring(0, 1).equals("9"))
        {
        }
        else
            intFlag = intFlag + 100000;
//        Date date=new Date();		//获取当前系统的时间
//        Timestamp sendValidateTime=new Timestamp(date.getTime());
        Date sendValidateTime =new Date();
        String yzm=String.valueOf(intFlag);
        Validate validateBean=validateService.queryYZM(tel);
        Date dbdate=new Date();
        long result=0;
        if(validateBean!=null&&validateBean.getTel()!=null) {
            dbdate=validateBean.getDate();
            result=sendValidateTime.getTime()-dbdate.getTime();
            if(result<=60000)
            {
                System.out.println("一个手机号一分钟只能获取一个验证码！");
                String jsonStr = "{\"errorCode\":\"11\",\"errorMessage\":\"一个手机号一分钟只能获取一个验证码！\"}";
                return  jsonStr;
            }
            else {
                validateService.updateYZM(tel, yzm, sendValidateTime);
                String jsonStr = "{\"errorCode\":\"01\",\"errorMessage\":\"phone or password is error\"}";
                System.out.println("**************************************************");
                System.out.println(" 敬爱的"+tel+"用户，此次生成的验证码为："+intFlag+"。有效期为1分钟！");
                System.out.println("**************************************************");
                return jsonStr;
            }
        }
        else {
            validateService.insertYZM(tel, yzm, sendValidateTime);
            String jsonStr = "{\"errorCode\":\"00\",\"errorMessage\":\"phone or password is error\"}";
            System.out.println("**************************************************");
            System.out.println(" 敬爱的"+tel+"用户，此次生成的验证码为："+intFlag+"。有效期为1分钟！");
            System.out.println("**************************************************");
            return jsonStr;
        }
    }

    //生成图形验证码
    @RequestMapping("/validateImage")
    @ResponseBody
    public void ValidateGenerate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);//创建图像缓冲区

        Graphics g = bi.getGraphics(); //通过缓冲区创建一个画布

        Color c = new Color(255, 255, 255); //创建颜色
        /*根据背景画了一个矩形框
         */
        g.setColor(c);//为画布创建背景颜色

        g.fillRect(0, 0, 68, 22); //fillRect:填充指定的矩形
        // X和Y用于指定矩形左上角也就是相对于原点的位置，width和height用于指定矩形的宽和高。

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();//转化为字符型的数组
        Random r = new Random();
        int len = ch.length;
        int index; //index用于存放随机数字
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);//产生随机数字
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  //设置颜色随机
            g.drawString(ch[index] + "", (i * 15) + 3, 18);//画数字以及数字的位置
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("piccode", sb.toString()); //将数字保留在session中，便于后续的使用
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    //图形验证码判断
    @RequestMapping("/ValidateImage")
    @ResponseBody
    public String ValidateImage(@RequestParam(value = "validateCode")String validateCode, HttpServletRequest request){

        HttpSession session=request.getSession();
        String piccode=(String) session.getAttribute("piccode");


        System.out.println("validateCode="+validateCode+".............piccode="+piccode);
        if(validateCode.equals(piccode)) {
            String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"success\"}";
            return jsonStr;
        }
        else {
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"phone or password is error\"}";
            return jsonStr;
        }
    }
}
