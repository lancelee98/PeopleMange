package cn.stylefeng.guns.modular.info_payment.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.InfoPayment;
import cn.stylefeng.guns.modular.info_payment.service.IInfoPaymentService;

/**
 * 缴费记录控制器
 *
 * @author fengshuonan
 * @Date 2018-11-16 21:08:10
 */
@Controller
@RequestMapping("/infoPayment")
public class InfoPaymentController extends BaseController {

    private String PREFIX = "/info_payment/infoPayment/";

    @Autowired
    private IInfoPaymentService infoPaymentService;

    /**
     * 跳转到缴费记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "infoPayment.html";
    }

    /**
     * 跳转到添加缴费记录
     */
    @RequestMapping("/infoPayment_add")
    public String infoPaymentAdd() {
        return PREFIX + "infoPayment_add.html";
    }

    /**
     * 跳转到修改缴费记录
     */
    @RequestMapping("/infoPayment_update/{infoPaymentId}")
    public String infoPaymentUpdate(@PathVariable Integer infoPaymentId, Model model) {
        InfoPayment infoPayment = infoPaymentService.selectById(infoPaymentId);
        model.addAttribute("item",infoPayment);
        LogObjectHolder.me().set(infoPayment);
        return PREFIX + "infoPayment_edit.html";
    }

    /**
     * 获取缴费记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return infoPaymentService.selectList(null);
    }

    /**
     * 新增缴费记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InfoPayment infoPayment) {
        infoPaymentService.insert(infoPayment);
        return SUCCESS_TIP;
    }

    /**
     * 删除缴费记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer infoPaymentId) {
        infoPaymentService.deleteById(infoPaymentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改缴费记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InfoPayment infoPayment) {
        infoPaymentService.updateById(infoPayment);
        return SUCCESS_TIP;
    }

    /**
     * 缴费记录详情
     */
    @RequestMapping(value = "/detail/{infoPaymentId}")
    @ResponseBody
    public Object detail(@PathVariable("infoPaymentId") Integer infoPaymentId) {
        return infoPaymentService.selectById(infoPaymentId);
    }
}
