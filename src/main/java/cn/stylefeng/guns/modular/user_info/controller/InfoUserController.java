package cn.stylefeng.guns.modular.user_info.controller;

import cn.stylefeng.guns.modular.system.model.Info;
import cn.stylefeng.guns.modular.system.warpper.DictWarpper;
import cn.stylefeng.guns.modular.system.warpper.InfoUserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.InfoUser;
import cn.stylefeng.guns.modular.user_info.service.IInfoUserService;

import java.util.List;
import java.util.Map;

/**
 * 用户信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-16 21:08:52
 */
@Controller
@RequestMapping("/infoUser")
public class InfoUserController extends BaseController {

    private String PREFIX = "/user_info/infoUser/";

    @Autowired
    private IInfoUserService infoUserService;
    @Autowired
    private IInfoUserService iInfoUserService;

    /**
     * 跳转到用户信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "infoUser.html";
    }

    /**
     * 跳转到添加用户信息
     */
    @RequestMapping("/infoUser_add")
    public String infoUserAdd() {
        return PREFIX + "infoUser_add.html";
    }

    /**
     * 跳转到修改用户信息
     */
    @RequestMapping("/infoUser_update/{infoUserId}")
    public String infoUserUpdate(@PathVariable Integer infoUserId, Model model) {
        InfoUser infoUser = infoUserService.selectById(infoUserId);
        model.addAttribute("item",infoUser);
        LogObjectHolder.me().set(infoUser);
        if(infoUser.getRoomId()!=0)
            model.addAttribute("pName", iInfoUserService.selectById(infoUser.getRoomId()).getName());
        else    model.addAttribute("pName","顶级");
        return PREFIX + "infoUser_edit.html";
    }

    /**
     * 获取用户信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition)
    {
        List<Map<String, Object>> list=infoUserService.list(condition);
        return super.warpObject(new InfoUserWarpper(list));
    }

    /**
     * 新增用户信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InfoUser infoUser) {
        infoUserService.insert(infoUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer infoUserId) {
        infoUserService.deleteById(infoUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InfoUser infoUser) {
        infoUserService.updateById(infoUser);
        return SUCCESS_TIP;
    }

    /**
     * 用户信息详情
     */
    @RequestMapping(value = "/detail/{infoUserId}")
    @ResponseBody
    public Object detail(@PathVariable("infoUserId") Integer infoUserId) {
        return infoUserService.selectById(infoUserId);
    }
}
