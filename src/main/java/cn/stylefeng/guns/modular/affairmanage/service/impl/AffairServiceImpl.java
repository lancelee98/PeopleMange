package cn.stylefeng.guns.modular.affairmanage.service.impl;

import cn.stylefeng.guns.modular.system.model.Affair;
import cn.stylefeng.guns.modular.system.dao.AffairMapper;
import cn.stylefeng.guns.modular.affairmanage.service.IAffairService;
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
 * @since 2018-11-10
 */
@Service
public class AffairServiceImpl extends ServiceImpl<AffairMapper, Affair> implements IAffairService {
    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }

    @Override
    public List<Affair> selectByIdNum(Integer idNumber) {
        return this.baseMapper.selectByIdNum(idNumber);
    }
}
