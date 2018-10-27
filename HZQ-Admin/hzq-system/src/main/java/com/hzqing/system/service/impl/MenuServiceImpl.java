package com.hzqing.system.service.impl;

import com.hzqing.common.util.StringUtils;
import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.Menu;
import com.hzqing.system.mapper.MenuMapper;
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
    /**
     * 根据条件检索列表
     * @param menu
     * @return
     */
    public List<Menu> selectTableList(Menu menu) {
        return menuMapper.selectTableList();
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
     * @param ids Menu Id数组
     * @return 返回影响行
     */
    public int deleteMenuByIds(String ids) {
        String[] menuIds = ids.split(",");
        return menuMapper.deleteMenuByIds(menuIds);
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
