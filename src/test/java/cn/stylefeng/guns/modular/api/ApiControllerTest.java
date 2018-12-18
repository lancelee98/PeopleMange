package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.GunsApplication;
import cn.stylefeng.guns.modular.system.model.InfoRent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= GunsApplication.class)
public class ApiControllerTest {
    @Autowired ApiController apiController;
    @Test
    public void  test()
    {
//        try {
//            String beginDate="2018-11-10";
//            String endDate="2018-12-9";
//            for(int j=0;j<20;j++){
//                for(int i=17;i<47;i++)
//                {
//
//                    Date start=randomDate(beginDate,endDate);
//                    Calendar calendar   =   new GregorianCalendar();
//                    calendar.setTime(start);
//                    int tem=1;
//                    if(i%2==0)tem=2;
//                    calendar.add(calendar.DATE,tem);//把日期往后增加一天.整数往后推,负数往前移动 
//                    Date end=calendar.getTime();
//                    apiController.rentCarport(i,start);
//                    //Thread.sleep(1000);
//                    apiController.stopRent(i,randomDate(start,end));
//                }
//            }
//        }
//        catch (Exception e)
//        {
//
//        }

    }
    public void testRondomDate() {
        Date date = randomDate("2018-11-09","2018-12-09");
        System.out.println(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date));
    }


    /**
     * 获取随机日期
     * @param beginDate 起始日期，格式为：yyyy-MM-dd
     * @param endDate 结束日期，格式为：yyyy-MM-dd
     * @return
     */
    private static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }

            long date = random(start.getTime(),end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Date randomDate(Date beginDate,Date endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = beginDate;
            Date end = endDate;

            if(start.getTime() >= end.getTime()){
                return null;
            }

            long date = random(start.getTime(),end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

}