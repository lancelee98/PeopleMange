package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("info_carport")
public class InfoCarport extends Model<InfoCarport> {

    private static final long serialVersionUID = 1L;

    /**
     * 车位标识号
     */
    @TableId(value = "carport_id", type = IdType.AUTO)
    private Integer carportId;
    /**
     * 车位地址
     */
    @TableField("carport_loc")
    private String carportLoc;

    private Integer isUsed;

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public Integer getCarportId() {
        return carportId;
    }

    public void setCarportId(Integer carportId) {
        this.carportId = carportId;
    }

    public String getCarportLoc() {
        return carportLoc;
    }

    public void setCarportLoc(String carportLoc) {
        this.carportLoc = carportLoc;
    }

    @Override
    protected Serializable pkVal() {
        return this.carportId;
    }

    @Override
    public String toString() {
        return "InfoCarport{" +
        ", carportId=" + carportId +
        ", carportLoc=" + carportLoc +
        "}";
    }
}
