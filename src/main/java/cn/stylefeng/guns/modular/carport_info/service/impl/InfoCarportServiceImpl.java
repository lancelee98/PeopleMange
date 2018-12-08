package cn.stylefeng.guns.modular.carport_info.service.impl;

import cn.stylefeng.guns.modular.system.model.InfoCarport;
import cn.stylefeng.guns.modular.system.dao.InfoCarportMapper;
import cn.stylefeng.guns.modular.carport_info.service.IInfoCarportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichuang
 * @since 2018-11-16
 */
@Service
public class InfoCarportServiceImpl extends ServiceImpl<InfoCarportMapper, InfoCarport> implements IInfoCarportService {
    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
