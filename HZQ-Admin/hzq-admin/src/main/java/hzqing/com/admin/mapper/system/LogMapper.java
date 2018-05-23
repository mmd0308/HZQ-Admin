package hzqing.com.admin.mapper.system;

import hzqing.com.admin.entity.system.Log;
import hzqing.com.common.base.mapper.BaseMapper;
import java.util.List;

public interface LogMapper extends BaseMapper<Log> {
    int deleteByPrimaryKey(String id);

    int insert(Log record);

    Log selectByPrimaryKey(String id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);
}