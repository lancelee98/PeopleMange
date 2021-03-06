package cn.stylefeng.guns.modular.rent_info.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.constant.state.BizLogType;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.model.OperationLog;
import cn.stylefeng.guns.modular.system.warpper.CarportWrapper;
import cn.stylefeng.guns.modular.system.warpper.LogWarpper;
import cn.stylefeng.guns.modular.system.warpper.RentWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.InfoRent;
import cn.stylefeng.guns.modular.rent_info.service.IInfoRentService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 租借信息控制器
 *
 * @author lichuang
 * @Date 2018-11-16 21:06:13
 */
@Controller
@RequestMapping("/infoRent")
public class InfoRentController extends BaseController {

    private String PREFIX = "/rent_info/infoRent/";

    @Autowired
    private IInfoRentService infoRentService;

    /**
     * 跳转到租借信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "infoRent.html";
    }

    /**
     * 跳转到添加租借信息
     */
    @RequestMapping("/infoRent_add")
    public String infoRentAdd() {
        return PREFIX + "infoRent_add.html";
    }

    /**
     * 跳转到修改租借信息
     */
    @RequestMapping("/infoRent_update/{infoRentId}")
    public String infoRentUpdate(@PathVariable Integer infoRentId, Model model) {
        InfoRent infoRent = infoRentService.selectById(infoRentId);
        model.addAttribute("item",infoRent);
        LogObjectHolder.me().set(infoRent);
        return PREFIX + "infoRent_edit.html";
    }

    /**
     * 获取租借信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer state,String starttime,String endtime) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
        List<Map<String, Object>> result = this.infoRentService.selectFromView(page,state,starttime,endtime);
        page.setRecords(result);
        System.out.println("mylc"+new PageInfoBT<>(page));
        return new PageInfoBT<>(page);
//        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
//        List<Map<String, Object>> result = operationLogService.getOperationLogs(page, beginTime, endTime, logName, BizLogType.valueOf(logType), page.getOrderByField(), page.isAsc());
//        page.setRecords(new LogWarpper(result).wrap());
//        return new PageInfoBT<>(page);
    }

    /**
     * 新增租借信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InfoRent infoRent) {
        infoRentService.insert(infoRent);
        return SUCCESS_TIP;
    }

    /**
     * 删除租借信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer infoRentId) {
        infoRentService.deleteById(infoRentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改租借信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InfoRent infoRent) {
        infoRentService.updateById(infoRent);
        return SUCCESS_TIP;
    }

    /**
     * 租借信息详情
     */
    @RequestMapping(value = "/detail/{infoRentId}")
    @ResponseBody
    public Object detail(@PathVariable("infoRentId") Integer infoRentId) {
        return infoRentService.selectById(infoRentId);
    }
}
