package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Affair;
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
 * @since 2018-11-10
 */
public interface AffairMapper extends BaseMapper<Affair> {
    List<Map<String, Object>> list(@Param("condition") String condition);
    List<Affair> selectByIdNum(@Param("idNumber") Integer idNumber);
}
