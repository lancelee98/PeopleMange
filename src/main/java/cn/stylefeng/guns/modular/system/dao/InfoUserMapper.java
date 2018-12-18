package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.InfoUser;
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
public interface InfoUserMapper extends BaseMapper<InfoUser> {

    List<Map<String, Object>> list(@Param("condition") String condition);
    Map<String, Object> getSexAndAgeCharts(Map<String, Object> map);
}
