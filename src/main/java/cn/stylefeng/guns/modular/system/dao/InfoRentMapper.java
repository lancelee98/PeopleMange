package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.InfoRent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lichuang
 * @since 2018-11-16
 */
public interface InfoRentMapper extends BaseMapper<InfoRent> {
    String selectNameByCarportId(@Param("carport_id") Integer carport_id);

    List<Map<String, Object>> list(@Param("page") Page<Map<String, Object>> page, @Param("condition") String condition);

    List<InfoRent> selectRentByIdNum(@Param("user_id") Integer user_id);
    int stopRent(@Param("payment") Float payment
            , @Param("start_time") Date start_time
            , @Param("end_time") Date end_time
            , @Param("carport_id") Integer carport_id
            , @Param("state") Integer state);

    List<Map<String, Object>> selectFromView(@Param("page") Page<Map<String, Object>> page,@Param("state") Integer state,
                                             @Param("starttime") String starttime,@Param("endtime") String endtime);


    List<Map<String, Object>> selectRentChart(Map<String, Object> map);
}
