package hzqing.com.admin.service.system;


import hzqing.com.admin.entity.system.Role;
import hzqing.com.common.base.service.IBaseService;

import java.util.HashMap;
import java.util.List;

public interface IRoleService extends IBaseService<Role> {

    int addRoleRes(HashMap<String, Object> resouce);

    List<Role> getAllEnabledRole();

    List<Role> getRoleByUserId(String id);
}
