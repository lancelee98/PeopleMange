package cn.stylefeng.guns.modular.datastatstic;

import cn.stylefeng.guns.modular.rent_info.service.impl.InfoRentServiceImpl;
import cn.stylefeng.guns.modular.user_info.service.impl.InfoUserServiceImpl;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 数据分析管理器
 * lichuang
 */
@Controller
@RequestMapping("/data_statistics")
public class Data_statisticsController extends BaseController {

    @Autowired private
    InfoUserServiceImpl infoUserService;
    @Autowired private
    InfoRentServiceImpl infoRentService;
    private String PREFIX = "/data_statistics/";


    @RequestMapping("")
    public String index() {
        return PREFIX + "data_statistics.html";
    }


    /**
     *获取性别比例和年龄分布
     */
    @RequestMapping(value = "/getAgeAndSEX")
    @ResponseBody
    public Object getSexCharts (@RequestParam Map<String, Object> paramMap ) {
        Map<String, Object> charts = this.infoUserService.getSexAndAgeCharts(paramMap);
        return  charts;
    }

    /**
     *获取人流量信息
     */
    @RequestMapping(value = "/people")
    @ResponseBody
    public Object selectRentChart (@RequestParam Map<String, Object> paramMap) {
        List<Map<String, Object>> charts = this.infoRentService.selectRentChart(paramMap);
        return  charts;
    }
}

