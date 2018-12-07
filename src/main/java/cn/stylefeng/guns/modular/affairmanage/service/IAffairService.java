package cn.stylefeng.guns.modular.affairmanage.service;

import cn.stylefeng.guns.modular.system.model.Affair;
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
 * @since 2018-11-10
 */
public interface IAffairService extends IService<Affair> {
    List<Map<String, Object>> list(@Param("condition") String condition);
}
