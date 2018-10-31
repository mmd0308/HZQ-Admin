package com.hzqing.system.mapper;

import com.hzqing.system.domain.Menu;

import java.util.List;
/**
 *
 * @description 菜单表
 * @author hzqing
 */
public interface MenuMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<Menu> selectTableList(Menu menu);
    /**
     * 新增 Menu
     * @param menu
     * @return
     */
    int insertMenu(Menu menu);

    /**
    * 根据menuId获取用户信息
    * @param menuId
    * @return 返回Menu
    */
    Menu selectMenuById(String menuId);

    /**
    * 根据menuId批量删除Menu
    * @param menuIds 用户Id数组
    * @return 返回影响行
    */
    int deleteMenuById(String menuIds);

    /**
    * 修改Menu
    * @param menu
    * @return 返回影响行数
    */
    int updateMenu(Menu menu);
}
