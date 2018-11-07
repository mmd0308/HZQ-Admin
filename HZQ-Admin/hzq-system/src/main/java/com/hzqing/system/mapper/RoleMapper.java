package com.hzqing.system.mapper;

import com.hzqing.system.domain.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<Role> selectTableList(Role role);
    /**
     * 新增 Role
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
    * 根据roleId获取用户信息
    * @param roleId
    * @return 返回Role
    */
    Role selectRoleById(String roleId);

    /**
    * 根据roleId批量删除Role
    * @param roleIds 用户Id数组
    * @return 返回影响行
    */
    int deleteRoleByIds(String roleIds);

    /**
    * 修改Role
    * @param role
    * @return 返回影响行数
    */
    int updateRole(Role role);

    /**
     * 根据用户的id获取用户的角色
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(String userId);

    /**
     * 检验编码是否重复
     * @param role
     * @return
     */
    List<Role> checkPermission(Role role);
}
