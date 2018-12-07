package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichuang
 * @since 2018-11-10
 */
@TableName("info_affair")
public class Affair extends Model<Affair> {

    private static final long serialVersionUID = 1L;

    /**
     * 事务id
     */
    @TableId(value = "affair_id", type = IdType.AUTO)
    private Integer affairId;
    /**
     * 身份证号码
     */
    @TableField("id_number")
    private Integer idNumber;
    /**
     * 管理员编号
     */
    @TableField("admin_id")
    private Integer adminId;
    /**
     * 请求时间
     */
    @TableField("creat_time")
    private Date creatTime;
    /**
     * 具体内容
     */
    private String content;
    /**
     * 图片描述
     */
    private String title;
    /**
     * 回执

内容
     */
    private String receipt;
    /**
     * 是否解决
     */
    private Integer solved;


    public Integer getAffairId() {
        return affairId;
    }

    public void setAffairId(Integer affairId) {
        this.affairId = affairId;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String imageLink) {
        this.title = imageLink;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    @Override
    protected Serializable pkVal() {
        return this.affairId;
    }

    @Override
    public String toString() {
        return "Affair{" +
        ", affairId=" + affairId +
        ", idNumber=" + idNumber +
        ", adminId=" + adminId +
        ", creatTime=" + creatTime +
        ", content=" + content +
        ", imageLink=" + title +
        ", receipt=" + receipt +
        ", solved=" + solved +
        "}";
    }
}
