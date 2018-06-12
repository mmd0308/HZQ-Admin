package hzqing.com.admin.service.system.impl;


import hzqing.com.admin.entity.system.Role;
import hzqing.com.admin.mapper.system.RoleMapper;
import hzqing.com.admin.service.system.IRoleService;
import hzqing.com.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper,Role> implements IRoleService {
    @Transactional
    @Override
    public int addRoleRes(HashMap<String, Object> resouce) {
        String roleId = (String) resouce.get("roleId");
        String menuId = (String) resouce.get("menuId");
        String buttons = (String) resouce.get("buttons");
        return addBatchRoleResource(menuId,roleId,"menu") + addBatchRoleResource(buttons,roleId,"button");
    }
    /**
     * 根据角色id，资源类型，更新资源
     * @param resource
     * @param roleId
     * @param resType
     * @return
     */
    private int addBatchRoleResource( String resource,String roleId, String resType) {
        //首先删除角色绑定的资源和类型
        HashMap<String,String> params = new HashMap<>();
        params.put("roleId",roleId);
        params.put("resourceType",resType);
        mapper.deleteRoleMenuButtonByRid(params);
        String[] split = resource.split(",");
        List<Map<String,String>> lists = new ArrayList<>();
        for (String s : split) {
            Map<String,String> m = new HashMap();
            m.put("roleId",roleId);
            m.put("resId",s);
            m.put("resType",resType);
            lists.add(m);
        }
        return mapper.addBatchRoleMenuButton(lists);
    }
}


