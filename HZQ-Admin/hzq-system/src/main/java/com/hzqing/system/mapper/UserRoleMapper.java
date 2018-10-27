package com.hzqing.system.mapper;

import com.hzqing.system.domain.UserRole;

import java.util.List;
/**
 *
 * @description 用户角色关系表
 * @author hzqing.com
 */
public interface UserRoleMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<UserRole> selectTableList();
    /**
     * 新增 UserRole
     * @param userrole
     * @return
     */
    int insertUserRole(UserRole userrole);

    /**
    * 根据userroleId获取用户信息
    * @param userroleId
    * @return 返回UserRole
    */
    UserRole selectUserRoleById(String userroleId);

    /**
    * 根据userroleId批量删除UserRole
    * @param userroleIds 用户Id数组
    * @return 返回影响行
    */
    int deleteUserRoleByIds(String[] userroleIds);

    /**
    * 修改UserRole
    * @param userrole
    * @return 返回影响行数
    */
    int updateUserRole(UserRole userrole);
}
