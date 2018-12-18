/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.dictmap.NoticeMap;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.affairmanage.service.impl.AffairServiceImpl;
import cn.stylefeng.guns.modular.room_info.service.IInfoService;
import cn.stylefeng.guns.modular.system.dao.InfoCarportMapper;
import cn.stylefeng.guns.modular.system.dao.InfoRentMapper;
import cn.stylefeng.guns.modular.system.dao.UserMapper;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.system.service.impl.NoticeServiceImpl;
import cn.stylefeng.guns.modular.system.warpper.InfoUserWarpper;
import cn.stylefeng.guns.modular.user_info.service.IInfoUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口控制器提供
 *
 * @author lichuang
 * @Date 2018/12/08 16:39
 */
@RestController
@RequestMapping("/gunsApi")
@Api(value = "人员管理的手机端API")
public class ApiController extends BaseController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  IInfoUserService userService;
    @Autowired
    private IInfoService roomService;
    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private AffairServiceImpl affairService;

    @Autowired
    private InfoCarportMapper infoCarportMapper;
    @Autowired
    private InfoRentMapper infoRentMapper;

    /**
     * api登录接口，通过账号密码获取token
     */
    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    @ApiOperation(value="使用API需要验证，账号admin密码111111", notes="验证通过后会返回一个token，之后每个请求的HEADER里都要加上Bearer +token" +
            "例如：headers: {" +
            "    'Authorization': 'Bearer ' + token" +
            "  }")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "admin", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "111111", required = true, dataType = "String")
    })
    public Object auth(@RequestParam("username") String username,
                       @RequestParam("password") String password) {

        //封装请求账号密码为shiro可验证的token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());

        //获取数据库中的账号密码，准备比对
        User user = userMapper.getByAccount(username);

        String credentials = user.getPassword();
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(), credentials, credentialsSalt, "");

        //校验用户账号密码
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(
                usernamePasswordToken, simpleAuthenticationInfo);

        if (passwordTrueFlag) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
            return result;
        } else {
            return new ErrorResponseData(500, "账号密码错误！");
        }
    }

    @ApiOperation(value="新增人员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "人员id(不用填)", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "idNumber", value = "人员身份证号码",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name",  value = "姓名",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "gender",value="性别：1男0女", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "phone",  value = "电话",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "race",  value = "民族", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "job",  value = "工作",dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "roomId",  value = "房间编号，通过getRoomInfo获得",required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "passwd",value = "密码", required = true, dataType = "String"),
    })
    /**
     * 新增用户信息
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(InfoUser infoUser) {
        if(ToolUtil.isNotEmpty(infoUser.getName())&&ToolUtil.isNotEmpty(infoUser.getRoomId())&&ToolUtil.isNotEmpty(infoUser.getAge())&&ToolUtil.isNotEmpty(infoUser.getGender())
                &&ToolUtil.isNotEmpty(infoUser.getPasswd())&&ToolUtil.isNotEmpty(infoUser.getPhone())&&ToolUtil.isNotEmpty(infoUser.getIdNumber()))
        {
            try {
                Map<String,Object> map=new HashMap<>();
                map.put("phone",infoUser.getPhone());
                List<InfoUser> list=userService.selectByMap(map);
                if(list.size()!=0)
                    return new ErrorResponseData(500, "该手机号码已经注册过");
                userService.insert(infoUser);
                return SUCCESS_TIP;
            }
            catch (Exception e)
            {
                return new ErrorResponseData(500, "请检查roomId是否正确");
            }
        }
        else
            return new ErrorResponseData(500, "信息不完整！");
    }

    /**
     * 查询密码是否正确
     */
    @ApiOperation(value="查询密码是否正确，正确返回用户ID")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "手机号", required = true,dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码",required = true, dataType = "String"),
    })
    @RequestMapping(value = "/checkPassWord",method = RequestMethod.GET)
    @ResponseBody
    public Object checkUserName(@RequestParam(value="phone") String phone,
                                @RequestParam(value="password") String password) {
        if(ToolUtil.isNotEmpty(phone)&&ToolUtil.isNotEmpty(password))
        {
            Map<String,Object> map=new HashMap<>();
            map.put("phone",phone);
            map.put("passwd",password);
            List<InfoUser> list=userService.selectByMap(map);
            if(list!=null)
                if(list.size()==1)
                {
                    Map<String,Object> user_id=new HashMap<>();
                    user_id.put("user_id",list.get(0).getId());
                    return user_id;
                }
            return new ErrorResponseData(500, "用户名或密码错误");
        }
        else return new ErrorResponseData(500, "存在为空的参数");
    }

    /**
     * 获得房间信息
     */
    @ApiOperation(value="获得房间信息")
    @RequestMapping(value = "/getRoomInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getRoomInfo() {
        Map<String,Object> map=new HashMap<>();
        map.put("type",1);
        List<Info> list=roomService.selectByMap(map);
        return list;
    }

    /**
     * 获得最新一条公告
     */
    @ApiOperation(value="获得最新一条公告")
    @RequestMapping(value = "/getNotice",method = RequestMethod.GET)
    @ResponseBody
    public Object getNotice() {
        return noticeService.getRencentNotice();
    }


    /**
     * 发起投诉或报修
     */
    @ApiOperation(value="发起投诉或报修")
    @RequestMapping(value = "/postAffair",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true,dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "title", value = "投诉报修内容",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "content", value = "投诉报修标题",required = true, dataType = "String"),
    })
    @ResponseBody
    public Object postAffair(@RequestParam(value="userId") Integer userId,
                             @RequestParam(value="title") String title,
                             @RequestParam(value="content") String content) {
        if(ToolUtil.isNotEmpty(userId)&&ToolUtil.isNotEmpty(content))
        {
            try {
                Affair affair=new Affair();
                affair.setTitle(title);
                affair.setIdNumber(userId);
                affair.setContent(content);
                affair.setCreatTime(new Date());
                affair.insert();
                return SUCCESS_TIP;
            }
            catch(Exception e)
            {
                return new ErrorResponseData(500, "发起投诉或报修失败");
            }
        }
        else  return new ErrorResponseData(500, "请检查参数是否为空");

    }

    /**
     * 查看发起的投诉或报修
     */
    @ApiOperation(value="查看发起的投诉或报修")
    @RequestMapping(value = "/getAffair",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true,dataType = "int"),
    })
    @ResponseBody
    public Object getAffair(@RequestParam(value="userId") Integer userId) {
        if(ToolUtil.isNotEmpty(userId))
        {
            try {
                return  affairService.selectByIdNum(userId);
            }
            catch(Exception e)
            {
                return new ErrorResponseData(500, "查看发起的投诉或报修错误");
            }
        }
        else  return new ErrorResponseData(500, "请检查参数是否为空");

    }


    /**
     * 查看剩余车位数量
     */
    @ApiOperation(value="查看剩余车位数量")
    @RequestMapping(value = "/getCarportNum",method = RequestMethod.GET)
    @ResponseBody
    public Object getCarportNum() {
        return infoCarportMapper.getCarportNum();
    }

    /**
     * 租用车位 成功返回carport_loc和createtime
     */
    @ApiOperation(value="租用车位 成功返回车位地址carport_loc和创建时间createtime（一个人只能租用一个车位）")
    @RequestMapping(value = "/rentCarport",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true,dataType = "int"),
    })
    @ResponseBody
    public Object rentCarport(@RequestParam(value="userId") Integer userId) {
        if(ToolUtil.isNotEmpty(userId))
        {
            try {
                if ((Long) infoCarportMapper.getCarportNum().get("CarportNum")!=0)
                {
                    if(infoRentMapper.selectRentByIdNum(userId).size()!=0)
                        return new  ErrorResponseData(500, "您已租用了一个车位，不能在租用其他车位");
                    InfoCarport infoCarport=infoCarportMapper.rentCarport();
                    InfoRent infoRent=new  InfoRent();
                    if(infoCarport!=null)
                    {
//                        /**
//                         * 触发器完成删掉
//                         */
//                        infoCarport.setIsUsed(1);
//                        infoCarport.updateById();

                        infoRent.setCarportId(infoCarport.getCarportId());
                        infoRent.setIdNumber(userId);
                        infoRent.setStartTime(new Date());
                        infoRent.insert();
                        Map<String,Object> map=new HashMap<>();
                        map.put("carport_loc",infoCarport.getCarportLoc());
                        map.put("createtime",infoRent.getStartTime());
                        return map;
                    }
                    else return new  ErrorResponseData(500, "车位已用完");
                }
                else  return new  ErrorResponseData(500, "车位已用完");

            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ErrorResponseData(500, "租用车位错误");
            }
        }
        else  return new ErrorResponseData(500, "请检查参数是否为空");
    }

    /**
     * 结束租借
     */
    @ApiOperation(value="结束租借 成功返回租借时长rentTime和支付金额payment")
    @RequestMapping(value = "/stopRent",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true,dataType = "int"),
    })
    @ResponseBody
    public Object stopRent(@RequestParam(value="userId") Integer userId) {
        if(ToolUtil.isNotEmpty(userId))
        {
            try {
                 Map<String,Object> map=new HashMap<>();
                 map.put("id_number",userId);
                 map.put("state",0);
                 List<InfoRent> infoRents=infoRentMapper.selectByMap(map);
                 if(infoRents.size()==1)
                 {
                     InfoRent infoRent=infoRents.get(0);

//                     /**
//                      * 触发器完成删掉
//                      */
//                     InfoCarport infoCarport=new InfoCarport();
//                     infoCarport.setCarportId(infoRent.getCarportId());
//                     infoCarport.setIsUsed(0);
//                     infoCarport.updateById();

                     Date start=infoRent.getStartTime();
                     Date end=new Date();
                     long interval=(end.getTime()-start.getTime())/1000/60;
                     float payment;
                     if((interval/30)>0) payment=(interval/30)*5;
                     else payment=5;
                     infoRentMapper.stopRent(payment,start,end,infoRent.getCarportId(),1);
                     Map<String,Object> result=new HashMap<>();
                     result.put("rentTime",interval);
                     result.put("payment",payment);
                     return result;
                 }
                 else return new ErrorResponseData(500, "不存在该用户的租借订单或有多个订单");
                }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ErrorResponseData(500, "结束租借报修错误");
            }
        }
        else  return new ErrorResponseData(500, "请检查参数是否为空");
    }


    /**
     * 测试接口是否走鉴权
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test() {
        return SUCCESS_TIP;
    }

}

