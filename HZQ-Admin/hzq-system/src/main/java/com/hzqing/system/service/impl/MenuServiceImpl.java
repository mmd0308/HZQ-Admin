package com.hzqing.system.service.impl;

import com.hzqing.common.util.StringUtils;
import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.Menu;
import com.hzqing.system.domain.Role;
import com.hzqing.system.domain.RoleMenu;
import com.hzqing.system.mapper.MenuMapper;
import com.hzqing.system.mapper.RoleMenuMapper;
import com.hzqing.system.service.IMenuService;
import com.hzqing.system.vo.MenuVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @description 菜单表
 * @author hzqing
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    /**
     * 根据条件检索列表
     * @param menu
     * @return
     */
    public List<Menu> selectTableList(Menu menu) {
        return menuMapper.selectTableList(menu);
    }

    /**
     * 新增Menu
     * @param menu
     * @return
     */
    public int insertMenu(Menu menu) {
        menu.setMenuId(UUIDUtils.get32UUID());
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        if (StringUtils.isEmpty(menu.getParentId()))
            menu.setParentId("0");
        return menuMapper.insertMenu(menu);
    }

    /**
    * 根据menuId获取信息
    * @param menuId
    * @return
    */
    public Menu selectMenuById(String menuId) {
        return menuMapper.selectMenuById(menuId);
    }
    /**
     * 根据menuId批量删除Menu
     * @param id Menu Id数组
     * @return 返回影响行
     */
    public int deleteMenuById(String id) {
        // 判断是否有子集菜单
        Menu menu = new Menu();
        menu.setParentId(id);
        List<Menu> menus = this.selectTableList(menu);
        if (menus.size() > 0 ){
            return -1;
        }
        // 判断该菜单是否跟角色绑定
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenuId(id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectTableList(roleMenu);
        if (roleMenus.size() > 0) {
            return -1;
        }
        return menuMapper.deleteMenuById(id);
    }
    /**
     * 修改Menu
     * @param menu
     * @return 返回影响行数
     */
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 获取菜单的树行结构
     * @return
     */
    public List<MenuVO> selectMenuTree() {
        List<Menu> menus = this.selectTableList(new Menu());
        List<MenuVO> menuVOS = new ArrayList<MenuVO>(menus.size());
        for (Menu menu: menus) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu,menuVO);
            menuVOS.add(menuVO);
        }
        return getTree(menuVOS);
    }

    /**
     * 检验编码是否唯一
     * @param menu
     * @return
     */
    @Override
    public boolean checkPermission(Menu menu) {
        List<Menu> menus = menuMapper.checkPermission(menu);
        if (menus.size() == 0 )
            return true;
        return false;
    }

    /**
     * 将列表信息转换成树行结构
     * @param menus
     * @return
     */
    private List<MenuVO> getTree(List<MenuVO> menus) {
        for (MenuVO menu : menus) {
            // 如果不是顶级元素 顶级元父级别id为0
            if (StringUtils.isNotEmpty(menu.getParentId()) && !menu.getParentId().equals("0")){
                for (MenuVO m : menus) {
                    if (menu.getParentId().equals(m.getMenuId())) {
                        // 找到父级别
                        if (m.getChildren() == null) {
                            m.setChildren(new ArrayList<>());
                        }
                        m.getChildren().add(menu);
                    }
                }
            }
        }
        // 删除所有父级别id不是0的
        menus.removeIf(menvo -> menvo.getParentId() != null && !menvo.getParentId().equals("0"));
        return menus;
    }

}
