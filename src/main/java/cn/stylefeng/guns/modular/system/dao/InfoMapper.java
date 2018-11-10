package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.Info;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-10
 */
public interface InfoMapper extends BaseMapper<Info> {
    List<ZTreeNode> tree();

    List<Map<String, Object>> list(@Param("condition") String conditiion);
}
