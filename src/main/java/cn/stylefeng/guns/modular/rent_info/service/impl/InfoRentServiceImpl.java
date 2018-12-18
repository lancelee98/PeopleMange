package cn.stylefeng.guns.modular.rent_info.service.impl;

import cn.stylefeng.guns.modular.system.model.InfoRent;
import cn.stylefeng.guns.modular.system.dao.InfoRentMapper;
import cn.stylefeng.guns.modular.rent_info.service.IInfoRentService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Map<String, Object>> list(Page<Map<String, Object>> page, String condition) {
        return this.baseMapper.list(page,condition);
    }
    @Override
    public List<Map<String, Object>> selectFromView(Page<Map<String, Object>> page, Integer state,
                                                    String starttime,  String endtime) {
        return this.baseMapper.selectFromView(page,state,starttime,endtime);
    }

    @Override
    public List<Map<String, Object>> selectRentChart(Map<String, Object> map) {
        return this.baseMapper.selectRentChart(map);
    }
}
