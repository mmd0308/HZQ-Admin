package hzqing.com.admin.service.system;


import hzqing.com.admin.base.service.IBaseService;
import hzqing.com.admin.entity.system.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoleService extends IBaseService<Role> {
    List<Role> getAllEnabledRole();

    List<Role> getRoleByUserId(String id);

    int addRoleRes(HashMap<String, Object> resouce);

    Map<String,List<String>> getResIdByRoleId(String roleId);

    List<Role> getDefautsRole(String roleType);

}
