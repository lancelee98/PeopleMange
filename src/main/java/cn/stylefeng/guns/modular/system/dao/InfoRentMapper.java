package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.InfoRent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lichuang
 * @since 2018-11-16
 */
public interface InfoRentMapper extends BaseMapper<InfoRent> {
    String selectNameByCarportId(@Param("carport_id") Integer carport_id);
    List<Map<String, Object>> list(@Param("condition") String condition);
}
