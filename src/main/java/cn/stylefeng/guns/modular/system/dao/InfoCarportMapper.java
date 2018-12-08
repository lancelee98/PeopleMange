package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.InfoCarport;
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
public interface InfoCarportMapper extends BaseMapper<InfoCarport> {
    List<Map<String, Object>> list(@Param("condition") String condition);
}
