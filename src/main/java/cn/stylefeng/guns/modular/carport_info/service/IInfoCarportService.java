package cn.stylefeng.guns.modular.carport_info.service;

import cn.stylefeng.guns.modular.system.model.InfoCarport;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichuang
 * @since 2018-11-16
 */
public interface IInfoCarportService extends IService<InfoCarport> {
    List<Map<String, Object>> list(@Param("condition") String condition);
}
