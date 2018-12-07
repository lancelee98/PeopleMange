package cn.stylefeng.guns.modular.affairmanage.controller;

import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.room_info.service.IInfoService;
import cn.stylefeng.guns.modular.system.model.InfoUser;
import cn.stylefeng.guns.modular.system.warpper.AffairWrapper;
import cn.stylefeng.guns.modular.system.warpper.InfoUserWarpper;
import cn.stylefeng.guns.modular.user_info.service.IInfoUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.apache.commons.text.StringEscapeUtils;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String affairAdd(Model model) {
        super.setAttr("id", 0);
        return PREFIX + "post_affair.html";
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

        List<Map<String, Object>> list=affairService.list(condition);
        return super.warpObject(new AffairWrapper(list));
    }




    @Autowired private
    IInfoUserService iInfoUserService;
    /**
     * 新增事物管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Affair affair) {
        String idNumber=affair.getReceipt();
        affair.setReceipt(null);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id_number",idNumber);
        Integer user_id=0;
        List<InfoUser> infoUser =iInfoUserService.selectByMap(map);
        if(infoUser.size()==0) throw new ServiceException(BizExceptionEnum.IDNumberNull);
        else  user_id=Integer.valueOf(infoUser.get(0).getId().toString());
        if (ToolUtil.isOneEmpty(affair, affair.getTitle(), affair.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        String content=affair.getContent();
        content=content.replaceAll("(&\\s)+", "&");
        String nescapeStr2 = StringEscapeUtils.unescapeXml(content);
        affair.setContent(nescapeStr2);
        affair.setIdNumber(user_id);
        affair.setCreatTime(new Date());
        affair.insert();
        return SUCCESS_TIP;
    }

    @RequestMapping("/affair_add/{affairId}")
    public String add2(@PathVariable Integer affairId, Model model) {
        super.setAttr("id", affairId);
        return PREFIX + "post_affair.html";
    }

    @RequestMapping(value = "/content/{affairId}")
    public String content(@PathVariable Integer affairId, Model model) {
        Affair affair = affairService.selectById(affairId);
        super.setAttr("item", affair.getContent());
        LogObjectHolder.me().set(affair);
        return PREFIX + "content.html";
    }
    @RequestMapping(value = "/content2/{affairId}")
    public String content2(@PathVariable Integer affairId, Model model) {
        Affair affair = affairService.selectById(affairId);
        super.setAttr("item", affair.getReceipt()==null?"空":affair.getReceipt());
        LogObjectHolder.me().set(affair);
        return PREFIX + "content.html";
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
    @RequestMapping(value = "/update/{affairId}")
    @ResponseBody
    public Object update(@PathVariable Integer affairId,Affair affair) {
        affair.setAffairId(affairId);
        String content=affair.getReceipt();
        content=content.replaceAll("(&\\s)+", "&");
        String nescapeStr2 = StringEscapeUtils.unescapeXml(content);
        affair.setReceipt(nescapeStr2);
        affair.setAdminId(ShiroKit.getUser().getId());
        affair.setSolved(1);
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
