package com.hzqing.system.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.Role;
import com.hzqing.system.mapper.RoleMapper;
import com.hzqing.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 *
 * @description 角色管理
 * @author hzqing
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 根据条件检索列表
     * @param role
     * @return
     */
    public List<Role> selectTableList(Role role) {
        return roleMapper.selectTableList();
    }

    /**
     * 新增Role
     * @param role
     * @return
     */
    public int insertRole(Role role) {
        role.setRoleId(UUIDUtils.get32UUID());
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setDelFlag("N");
        return roleMapper.insertRole(role);
    }

    /**
    * 根据roleId获取信息
    * @param roleId
    * @return
    */
    public Role selectRoleById(String roleId) {
        return roleMapper.selectRoleById(roleId);
    }
    /**
     * 根据roleId批量删除Role
     * @param ids Role Id数组
     * @return 返回影响行
     */
    public int deleteRoleByIds(String ids) {
        String[] roleIds = ids.split(",");
        return roleMapper.deleteRoleByIds(roleIds);
    }
    /**
     * 修改Role
     * @param role
     * @return 返回影响行数
     */
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 根据用户的id获取角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> selectRolesByUserId(String userId) {
        return roleMapper.selectRolesByUserId(userId);
    }
}
