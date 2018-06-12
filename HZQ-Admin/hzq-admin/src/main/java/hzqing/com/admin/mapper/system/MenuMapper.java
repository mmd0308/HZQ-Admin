package hzqing.com.admin.mapper.system;

import hzqing.com.admin.entity.system.Menu;
import hzqing.com.common.base.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> getResIdByRoleIdAndType(Map<String, String> params);
}