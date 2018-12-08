package cn.stylefeng.guns.modular.carport_info.controller;

import cn.stylefeng.guns.modular.system.warpper.CarportWrapper;
import cn.stylefeng.guns.modular.system.warpper.RoomWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.InfoCarport;
import cn.stylefeng.guns.modular.carport_info.service.IInfoCarportService;

import java.util.List;
import java.util.Map;

/**
 * 车位信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-16 21:05:06
 */
@Controller
@RequestMapping("/infoCarport")
public class InfoCarportController extends BaseController {

    private String PREFIX = "/carport_info/infoCarport/";

    @Autowired
    private IInfoCarportService infoCarportService;

    /**
     * 跳转到车位信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "infoCarport.html";
    }

    /**
     * 跳转到添加车位信息
     */
    @RequestMapping("/infoCarport_add")
    public String infoCarportAdd() {
        return PREFIX + "infoCarport_add.html";
    }

    /**
     * 跳转到修改车位信息
     */
    @RequestMapping("/infoCarport_update/{infoCarportId}")
    public String infoCarportUpdate(@PathVariable Integer infoCarportId, Model model) {
        InfoCarport infoCarport = infoCarportService.selectById(infoCarportId);
        model.addAttribute("item",infoCarport);
        LogObjectHolder.me().set(infoCarport);
        return PREFIX + "infoCarport_edit.html";
    }

    /**
     * 获取车位信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.infoCarportService.list(condition);
        return super.warpObject(new CarportWrapper(list));
    }

    /**
     * 新增车位信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InfoCarport infoCarport) {
        infoCarportService.insert(infoCarport);
        return SUCCESS_TIP;
    }

    /**
     * 删除车位信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer infoCarportId) {
        infoCarportService.deleteById(infoCarportId);
        return SUCCESS_TIP;
    }

    /**
     * 修改车位信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InfoCarport infoCarport) {
        infoCarportService.updateById(infoCarport);
        return SUCCESS_TIP;
    }

    /**
     * 车位信息详情
     */
    @RequestMapping(value = "/detail/{infoCarportId}")
    @ResponseBody
    public Object detail(@PathVariable("infoCarportId") Integer infoCarportId) {
        return infoCarportService.selectById(infoCarportId);
    }
}
