package cn.stylefeng.guns.modular.affairmanage.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Affair;
import cn.stylefeng.guns.modular.affairmanage.service.IAffairService;

/**
 * 事物管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-10 22:54:47
 */
@Controller
@RequestMapping("/affair")
public class AffairController extends BaseController {

    private String PREFIX = "/affairmanage/affair/";

    @Autowired
    private IAffairService affairService;

    /**
     * 跳转到事物管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "affair.html";
    }

    /**
     * 跳转到添加事物管理
     */
    @RequestMapping("/affair_add")
    public String affairAdd() {
        return PREFIX + "affair_add.html";
    }

    /**
     * 跳转到修改事物管理
     */
    @RequestMapping("/affair_update/{affairId}")
    public String affairUpdate(@PathVariable Integer affairId, Model model) {
        Affair affair = affairService.selectById(affairId);
        model.addAttribute("item",affair);
        LogObjectHolder.me().set(affair);
        return PREFIX + "affair_edit.html";
    }

    /**
     * 获取事物管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return affairService.selectList(null);
    }

    /**
     * 新增事物管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Affair affair) {
        affairService.insert(affair);
        return SUCCESS_TIP;
    }

    /**
     * 删除事物管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer affairId) {
        affairService.deleteById(affairId);
        return SUCCESS_TIP;
    }

    /**
     * 修改事物管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Affair affair) {
        affairService.updateById(affair);
        return SUCCESS_TIP;
    }

    /**
     * 事物管理详情
     */
    @RequestMapping(value = "/detail/{affairId}")
    @ResponseBody
    public Object detail(@PathVariable("affairId") Integer affairId) {
        return affairService.selectById(affairId);
    }
}
