package cn.stylefeng.guns.modular.rent_info.service.impl;

import cn.stylefeng.guns.modular.system.model.InfoRent;
import cn.stylefeng.guns.modular.system.dao.InfoRentMapper;
import cn.stylefeng.guns.modular.rent_info.service.IInfoRentService;
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
public class InfoRentServiceImpl extends ServiceImpl<InfoRentMapper, InfoRent> implements IInfoRentService {
    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
