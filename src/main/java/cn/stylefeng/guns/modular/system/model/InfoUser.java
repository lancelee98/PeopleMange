package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichuang
 * @since 2018-11-16
 */
@TableName("info_user")
public class InfoUser extends Model<InfoUser> {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 身份证号码
     */
    @TableField("id_number")
    private String idNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 电话
     */
    private String phone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 民族
     */
    private String race;
    /**
     * 职业
     */
    private String job;
    /**
     * 区域标识号
     */
    @TableField("room_id")
    private Integer roomId;
    /**
     * 手机登录密码
     */
    private String passwd;


    public String getIdNumber() {
        return idNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    protected Serializable pkVal() {
        return this.idNumber;
    }

    @Override
    public String toString() {
        return "InfoUser{" +
        ", idNumber=" + idNumber +
        ", name=" + name +
        ", gender=" + gender +
        ", phone=" + phone +
        ", age=" + age +
        ", race=" + race +
        ", job=" + job +
        ", roomId=" + roomId +
        ", passwd=" + passwd +
        "}";
    }
}
