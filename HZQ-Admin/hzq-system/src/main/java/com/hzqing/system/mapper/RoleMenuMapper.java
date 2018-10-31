package com.hzqing.system.mapper;

import com.hzqing.system.domain.RoleMenu;

import java.util.List;
/**
 *
 * @description 角色和菜单关系表
 * @author hzqing.com
 */
public interface RoleMenuMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<RoleMenu> selectTableList(RoleMenu roleMenu);
    /**
     * 新增 RoleMenu
     * @param rolemenu
     * @return
     */
    int insertRoleMenu(RoleMenu rolemenu);

    /**
    * 根据rolemenuId获取用户信息
    * @param rolemenuId
    * @return 返回RoleMenu
    */
    RoleMenu selectRoleMenuById(String rolemenuId);

    /**
    * 根据rolemenuId批量删除RoleMenu
    * @param rolemenuIds 用户Id数组
    * @return 返回影响行
    */
    int deleteRoleMenuByIds(String[] rolemenuIds);

    /**
    * 修改RoleMenu
    * @param rolemenu
    * @return 返回影响行数
    */
    int updateRoleMenu(RoleMenu rolemenu);
}
