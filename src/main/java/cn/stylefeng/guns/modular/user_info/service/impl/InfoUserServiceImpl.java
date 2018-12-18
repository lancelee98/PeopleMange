package cn.stylefeng.guns.modular.user_info.service.impl;

import cn.stylefeng.guns.modular.system.model.InfoUser;
import cn.stylefeng.guns.modular.system.dao.InfoUserMapper;
import cn.stylefeng.guns.modular.user_info.service.IInfoUserService;
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
public class InfoUserServiceImpl extends ServiceImpl<InfoUserMapper, InfoUser> implements IInfoUserService {
    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
    @Override
    public Map<String, Object>  getSexAndAgeCharts(Map<String, Object> map) {
        return this.baseMapper.getSexAndAgeCharts(map);
    }
}
