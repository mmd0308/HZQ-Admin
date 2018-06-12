package hzqing.com.admin.service.system.impl;


import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.mapper.system.MenuMapper;
import hzqing.com.admin.service.system.IMenuService;
import hzqing.com.admin.vo.system.MenuVO;
import hzqing.com.common.base.service.impl.BaseServiceImpl;
import hzqing.com.common.constant.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper,Menu> implements IMenuService {
    @Override
    public List<MenuVO> getTree(String id) {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enabled", "1");
        example.orderBy("sort");
        List<Menu> vos = mapper.selectByExample(example);
        List<MenuVO> menuVOS = this.listByPId(vos, id);
        return listToTree(vos,menuVOS);
    }


    /**
     * 将list页面转成树行结构
     * @param vos
     * @param menuVOS
     * @return
     */
    private List<MenuVO> listToTree(List<Menu> vos, List<MenuVO> menuVOS) {
        for (MenuVO vo : menuVOS) {
            List<MenuVO> res = this.listByPId(vos, vo.getId());
            if (res.size() > 0) {
                vo.setChildren(res);
                this.listToTree(vos,res);
            }
        }
        return menuVOS;
    }

    /**
     * 根据父级别id，获取父级别列表
     * @param menus
     * @param parentId
     * @return
     */
    private List<MenuVO> listByPId(List<Menu> menus, String parentId) {
        List<MenuVO> menuVOS = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParentId().equals(parentId)) {
                MenuVO menuVO = new MenuVO();
                BeanUtils.copyProperties(menu,menuVO);
                menuVOS.add(menuVO);
            }
        }
        return menuVOS;
    }

    @Override
    public List<Menu> getListMenusByRids(String rids) {
        return mapper.getMenusByRids(rids);
    }

    @Override
    public Map<String, List<String>> getResIdByRoleId(String roleId) {
        Map<String,String> params = new HashMap<>();
        params.put("roleId",roleId);
        params.put("resType", Constant.RES_TYPE_MENU);
        List<String> menus = mapper.getResIdByRoleIdAndType(params);
        params.put("resType",Constant.RES_TYPE_BUTTON);
        List<String> buttons = mapper.getResIdByRoleIdAndType(params);
        Map<String,List<String>> res = new HashMap<>();
        res.put("menuIds",menus);
        res.put("buttonIds",buttons);
        return  res;
    }

    @Override
    public List<String> getResCodeByRoleIds(String roleIds) {
        return mapper.getResCodeByRoleIds(roleIds);
    }

    /**
     * 首先查询她有没有子集,如果有不能删除,没有进行删除
     * @param id
     * @return
     */
    @Override
    public int deletedById(String id) {
        Menu menu = new Menu();
        menu.setParentId(id);
        List<Menu> menus = this.findAll(menu);
        if (menus.size()>0){
            return 0;
        }else {
            super.deletedById(id);
            return  1;
        }
    }

}


