package hzqing.com.admin.mapper.system;

import hzqing.com.admin.entity.system.Role;
import hzqing.com.common.base.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {
    void deleteRoleMenuButtonByRid(HashMap<String, String> params);

    int addBatchRoleMenuButton(List<Map<String,String>> lists);
}