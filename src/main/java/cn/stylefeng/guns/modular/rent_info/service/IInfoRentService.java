package cn.stylefeng.guns.modular.rent_info.service;

import cn.stylefeng.guns.modular.system.model.InfoRent;
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
public interface IInfoRentService extends IService<InfoRent> {
    List<Map<String, Object>> list(@Param("condition") String condition);
}
