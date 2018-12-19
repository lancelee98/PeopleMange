package cn.stylefeng.guns.modular.room_info.controller;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.util.TreeTool;
import cn.stylefeng.guns.modular.system.warpper.DeptWarpper;
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
import cn.stylefeng.guns.modular.system.model.Info;
import cn.stylefeng.guns.modular.room_info.service.IInfoService;

import java.util.List;
import java.util.Map;
import static cn.stylefeng.guns.core.common.constant.factory.MutiStrFactory.*;
/**
 * 住房管理控制器
 *
 * @author lichuang
 * @Date 2018-11-10 14:57:27
 */
@Controller
@RequestMapping("/info")
public class InfoController extends BaseController {

    private String PREFIX = "/room_info/info/";

    @Autowired
    private IInfoService infoService;

    /**
     * 跳转到住房管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "info.html";
    }

    /**
     * 跳转到添加住房管理
     */
    @RequestMapping("/info_add")
    public String infoAdd() {
        return PREFIX + "info_add.html";
    }

    /**
     * 跳转到修改住房管理
     */
    @RequestMapping("/info_update/{infoId}")
    public String infoUpdate(@PathVariable Integer infoId, Model model) {
        Info info = infoService.selectById(infoId);
        model.addAttribute("item",info);
        if(info.getParentId()!=0)
        model.addAttribute("pName", infoService.selectById(info.getParentId()).getName());
        else    model.addAttribute("pName","顶级");
        LogObjectHolder.me().set(info);
        return PREFIX + "info_edit.html";
    }

    /**
     * 获取住房管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        List<Map<String, Object>> list = this.infoService.list(condition);
        return super.warpObject(new RoomWarpper(list));
    }

    /**
     * 新增住房信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Info info) {
        if(info.getParentId()!=0)
        info.setAddress(infoService.selectById(info.getParentId()).getAddress()+info.getName());
        else info.setAddress(info.getName());
        infoService.insert(info);
        return SUCCESS_TIP;
    }

    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.infoService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }
    /**
     * 删除住房管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer infoId) {
        List<Integer> list= TreeTool.removeTreeNode(infoService.list(null),infoId);
        for(Integer i :list)
            infoService.deleteById(i);
        return SUCCESS_TIP;
    }

    /**
     * 修改住房管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Info info) {
        Info oldinfo=infoService.selectById(info.getRoomId());
        String name=info.getName();
        String oldaddress=oldinfo.getAddress();
        String newaddress;
        if(info.getParentId()==0)newaddress=name;
        else newaddress=oldaddress.substring(0,oldaddress.length()-oldinfo.getName().length())+name;
        info.setAddress(newaddress);
        infoService.updateById(info);
        ChangeChildAddress(newaddress,oldaddress,info.getRoomId());
        return SUCCESS_TIP;
    }
    /**
     * 住房管理详情
     */
    @RequestMapping(value = "/detail/{infoId}")
    @ResponseBody
    public Object detail(@PathVariable("infoId") Integer infoId) {
        return infoService.selectById(infoId);
    }
}
