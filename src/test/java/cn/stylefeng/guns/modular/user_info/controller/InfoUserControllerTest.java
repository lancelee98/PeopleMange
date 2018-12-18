package cn.stylefeng.guns.modular.user_info.controller;

import cn.stylefeng.guns.GunsApplication;
import cn.stylefeng.guns.modular.affairmanage.service.impl.AffairServiceImpl;
import cn.stylefeng.guns.modular.api.ApiController;
import cn.stylefeng.guns.modular.carport_info.controller.InfoCarportController;
import cn.stylefeng.guns.modular.room_info.controller.InfoController;
import cn.stylefeng.guns.modular.system.dao.InfoCarportMapper;
import cn.stylefeng.guns.modular.system.dao.InfoRentMapper;
import cn.stylefeng.guns.modular.system.dao.NoticeMapper;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.system.service.impl.NoticeServiceImpl;
import cn.stylefeng.guns.modular.user_info.service.IInfoUserService;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= GunsApplication.class)
public class InfoUserControllerTest {

//    @Autowired InfoUserController infoUserController;
//    @Autowired
//    IInfoUserService iInfoUserService;
//    @Autowired
//    private NoticeServiceImpl noticeService;
//    @Autowired
//    AffairServiceImpl affairService;
//    @Autowired
//    InfoCarportMapper infoCarportMapper;
    @Autowired
    InfoRentMapper infoRentMapper;
    @Autowired
    InfoController infoController;
    @Autowired
    InfoCarportController infoCarportController;
    @Autowired
    ApiController apiController;
    @Autowired private InfoUserController infoUserController;
   @Test
    public void  test ()
    {



//        List<String> list=new ArrayList<>();
//        list.add("俊子");
//        list.add("杰驰");
//        list.add("晓杞");
//        list.add("桓浩");
//        list.add("晓祥");
//        list.add("吉良");
//        list.add("韦锋");
//        list.add("翰强");
//        list.add("腾勇");
//        list.add("嘉帝");
//        list.add("柔翰");
//        list.add("欣锋");
//        list.add("林枫");
//        list.add("佳远");
//        list.add("涛延");
//        list.add("伟辰");
//        list.add("帆中");
//        list.add("翱谷");
//        list.add("辰骏");
//        list.add("祜侠");
//        list.add("起子");
//        list.add("帆枫");
//        list.add("家帆");
//        list.add("仕伟");
//        list.add("晖晨");
//        list.add("梓初");
//        list.add("初仕");
//        list.add("裕欣");
//        list.add("升蔓");
//        list.add("梁翰");

        for(int i=0;i<30;i++)
        {
//            InfoUser infoUser=new InfoUser();
//            if(i%2==0)
//            {
//                infoUser.setAge(30+2*i);
//                infoUser.setGender(1);
//                infoUser.setJob("教室");
//            }
//            else
//            {
//                infoUser.setAge(30-i);
//                infoUser.setGender(0);
//                infoUser.setJob("律师");
//            }
//            infoUser.setPasswd("123456");
//            infoUser.setRace("汉族");
//            int temp=10+i;
//            infoUser.setIdNumber("4128211998053335"+temp);
//            infoUser.setPhone("134615429"+temp);
//            infoUser.setRoomId(57+i);
//            infoUser.setName(list.get(i));
//            infoUserController.add(infoUser);




//            InfoCarport infoCarport=new InfoCarport();
//            infoCarport.setCarportLoc("上海海事大学"+i+"号停车位");
//            infoCarportController.add(infoCarport);
        }


//        Map<String,Object> map=new HashMap<>();
//        map.put("id_number",1);
//        map.put("state",0);
//        List<InfoRent> infoRents=infoRentMapper.selectByMap(map);
//        if(infoRents.size()==1)
//        {
//            InfoRent infoRent=infoRents.get(0);
//
//            /**
//             * 触发器完成删掉
//             */
//            InfoCarport infoCarport=new InfoCarport();
//            infoCarport.setCarportId(infoRent.getCarportId());
//            infoCarport.setIsUsed(0);
//            infoCarport.updateById();
//
//            Date start=infoRent.getStartTime();
//            Date end=new Date();
//            long interval=(end.getTime()-start.getTime())/1000/60;
//            float payment;
//            if((interval/30)>0) payment=(interval/30)*10;
//            else payment=10;
//            infoRentMapper.stopRent(payment,start,end,infoRent.getCarportId(),1);
//
//            Map<String,Object> result=new HashMap<>();
//            result.put("rentTime",interval);
//            result.put("payment",payment);
//        }

//        Date a = new Date();
//        Date b = new Date();
//        long interval = (b.getTime() - a.getTime())/1000;
//        System.out.println("两个时间相差"+interval+"秒");//会打印出相差3秒
//
//        //Date ------> Date对象
//        //创建日期格式化对象   因为DateFormat类为抽象类 所以不能new
//        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//多态
//        //2017-04-19 星期三 下午 20:17:38
//
//        Date date = new Date();//创建时间
//        String format = bf.format(date);//格式化 bf.format(date);
//        //System.out.println(format);
//
//        //String ------->Date对象
//        String s = "2018-12-09 13:11:38";//有格式要求 必须和自定义模式严格一致
//        try {
//            Date parse = bf.parse(s);// df.parse(s);String转成对象
//            Date b = new Date();
//            System.out.println(parse);
//            long interval = (b.getTime() - parse.getTime())/1000/60;
//            float payment;
//            if((interval/30)>0) payment=(interval/30)*10;
//            else payment=10;
//             System.out.println("两个时间相差"+interval+"分钟"+payment);//会打印出相差3秒
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //infoCarportMapper.getCarportNum();
        //affairService.selectByIdNum(1);
//        Affair affair=new Affair();
//        affair.setCreatTime(new Date());
//        affair.setIdNumber(1);
//        affair.setContent("asfasf");
//        System.out.println(affair.getAffairId());
//        affair.insert();
//        System.out.println(affair.getAffairId());
//        noticeService.getRencentNotice();

//        Map<String,Object> map=new HashMap<>();
//        map.put("phone","123");
//        iInfoUserService.selectByMap(map);
//        //infoUserController.detail(1);
//        String s="& lt;p& gt;安抚 安抚& lt;/p& gt; ";
//        //s.replaceAll(" ", "&");
//        s=s.replaceAll("(&\\s)+", "&");
//        System.out.println(s);
//        String nescapeStr2 = StringEscapeUtils.unescapeXml(s);
//        System.out.println(nescapeStr2);
    }
}