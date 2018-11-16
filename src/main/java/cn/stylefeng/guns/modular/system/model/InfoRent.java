package cn.stylefeng.guns.modular.system.model;

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
@TableName("info_rent")
public class InfoRent extends Model<InfoRent> {

    private static final long serialVersionUID = 1L;

    /**
     * 车位标识号
     */
    @TableId("carport_id")
    private Integer carportId;
    /**
     * 发起人身份证号
     */
    @TableField("id_number")
    private String idNumber;
    /**
     * 租用时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 到期时间
     */
    @TableField("end_time")
    private Date endTime;


    public Integer getCarportId() {
        return carportId;
    }

    public void setCarportId(Integer carportId) {
        this.carportId = carportId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.carportId;
    }

    @Override
    public String toString() {
        return "InfoRent{" +
        ", carportId=" + carportId +
        ", idNumber=" + idNumber +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        "}";
    }
}
