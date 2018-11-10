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
 * @author stylefeng
 * @since 2018-11-10
 */
@TableName("room_info")
public class Info extends Model<Info> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域标识号
     */
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 父节点
     */
    @TableField("parent_id")
    private Integer parentId;


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.roomId;
    }

    @Override
    public String toString() {
        return "Info{" +
        ", roomId=" + roomId +
        ", type=" + type +
        ", name=" + name +
        ", address=" + address +
        ", parentId=" + parentId +
        "}";
    }
}
