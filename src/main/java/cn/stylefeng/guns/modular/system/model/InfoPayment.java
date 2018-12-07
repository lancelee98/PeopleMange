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
 * @since 2018-11-16
 */
@TableName("info_payment")
public class InfoPayment extends Model<InfoPayment> {

    private static final long serialVersionUID = 1L;

    /**
     * 缴费标识号
     */
    @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;
    /**
     * 缴费人身份证号码
     */
    @TableField("id_number")
    private Integer idNumber;
    /**
     * 缴费类型
     */
    private String type;
    /**
     * 缴费时间
     */
    private Date time;
    /**
     * 缴费金额
     */
    private Float payment;


    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    @Override
    protected Serializable pkVal() {
        return this.paymentId;
    }

    @Override
    public String toString() {
        return "InfoPayment{" +
        ", paymentId=" + paymentId +
        ", idNumber=" + idNumber +
        ", type=" + type +
        ", time=" + time +
        ", payment=" + payment +
        "}";
    }
}
