package hzqing.com.admin.service.system.impl;


import hzqing.com.admin.base.service.impl.BaseServiceImpl;
import hzqing.com.admin.entity.system.Dict;
import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.service.system.IDictService;
import hzqing.com.admin.service.system.IMenuService;
import hzqing.com.admin.service.system.IRoleService;
import hzqing.com.admin.vo.system.MenuVO;
import hzqing.com.common.util.LevelCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {

    public MenuServiceImpl() {
        super.mapper = "menuMapper";
    }
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDictService dictService;

    @Override
    public List<MenuVO> getTree(String id) {
        MenuVO menu = new MenuVO();
//        展示所有的菜单
//        menu.setEnabled("1");
        List<MenuVO> vos = (List<MenuVO>) baseDao.findForList(mapper+".queryMenuvo",menu);
        List<MenuVO> menuVOS = this.listByPId(vos, id);
        return listToTree(vos,menuVOS);
    }


    /**
     * 将list页面转成树行结构
     * @param vos
     * @param menuVOS
     * @return
     */
    private List<MenuVO> listToTree(List<MenuVO> vos, List<MenuVO> menuVOS) {
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
     * @param menuvos
     * @param parentId
     * @return
     */
    private List<MenuVO> listByPId(List<MenuVO> menuvos,String parentId) {
        List<MenuVO> menuVOS = new ArrayList<>();
        for (MenuVO vo :
                menuvos) {
            if (vo.getParentId().equals(parentId)) {
                menuVOS.add(vo);
            }
        }
        return menuVOS;
    }

    @Override
    public String getNextLevelCode(String parentId,String levelCode) {
        String maxLCodeByPid = getMaxLCodeByPid(parentId);
        return LevelCodeUtil.getNextLevelCode(maxLCodeByPid,levelCode);
    }



    @Override
    public List<MenuVO> getMenusByRids(Map<String,String> map, String id) {
        List<MenuVO> res = this.getListMenusByRids(map);
        List<MenuVO> menuVOS = this.listByPId(res, id);
        return listToTree(res,menuVOS);
    }


    @Override
    public List<Menu> getMenusByUid(String id) {
        return (List<Menu>) baseDao.findForList(mapper+".getMenusByUid",id);
    }

    @Override
    public List<MenuVO> getDefaultMenus() {
        MenuVO menuVO = new MenuVO();
        menuVO.setMenuType("NL");
        return this.getMenusVoTree(menuVO);
    }

    @Override
    public List<MenuVO> getMenusVoTree(MenuVO menuVO) {
        List<MenuVO> res = (List<MenuVO>) baseDao.findForList(mapper+".queryMenuvo",menuVO);
        res = listToTree(res);
        return res;
    }

    @Override
    public List<MenuVO> getMenusByAuthAndCode(String auth, String code) {
        Dict dict = dictService.getIdByCode(auth);
        if (dict == null)
            return null;
        Menu menu = new Menu();
        menu.setEnabled("1");
        menu.setMenuType(dict.getId());
        List<MenuVO> vos = (List<MenuVO>) baseDao.findForList(mapper+".queryMenuvo",menu);
        List<MenuVO> menuVOS = this.listByPId(vos, this.getMenuByCode(code).getId());
        return listToTree(vos,menuVOS);
    }
    @Override
    public Menu getMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);
        List<Menu> menus = this.findAll(menu);
        return menus.size() > 0 ? menus.get(0) : null;
    }

    @Override
    public List<MenuVO> getListMenusByRids(Map<String, String> maps) {
        List<MenuVO> res = (List<MenuVO>) baseDao.findForList(mapper+".getMenusByRids",maps);
        return res;
    }

    /**
     * 采用递归组装树
     * @param menus
     * @return
     */
    private List<Menu> trees(List<Menu> menus){
        for (Menu m : menus){
            List<Menu> menuList = getMenuByPId(m.getId());
            m.setMenus(menuList);
            trees(menuList);
        }
        return  menus;
    }

    /**
     * 根据父id获取menu数据
     * @param id
     * @return
     */
    private List<Menu> getMenuByPId(String id){
        return (List<Menu>)baseDao.findForList(mapper+".getMenuByPId",id);
    }

    /**
     * 根据父id获取子集中做大的levelcode
     * @param pId
     * @return
     */
    private String getMaxLCodeByPid(String pId){
        return (String) baseDao.findForObject(mapper+".getMaxLCodeByPid",pId);
    }

    /**
     * 首先查询她有没有子集,如果有不能删除,没有进行删除
     * @param id
     * @return
     */
    @Override
    public int deletedById(String id) {
        List<Menu> menus = this.getMenuByPId(id);
        if (menus.size()>0){
            return 0;
        }else {
            super.deletedById(id);
            return  1;
        }
    }

    /**
     * 根据列表转成树形结构
     * @param list
     * @return
     */
    //@TODO 暂时保留，等会删除
    private List<MenuVO> listToTree(List<MenuVO> list){
        List<MenuVO> res = new ArrayList<>();
        int length = getMinLenthLevelCode(list); // 获取顶层菜单的levelcode长度
        if (length == 0){
            return null;
        }
        for (MenuVO menuvo : list) {
           // if (menuvo.getLevelcode().length() == length)
            res.add(menuvo);
        }
//        for (MenuVO menuVO: res) {
//            for (MenuVO m: list) {
//                if (menuVO.getId() != null && menuVO.getId().equals(m.getParentId())){
//                    if (menuVO.getMenusvo() == null){
//                        menuVO.setMenusvo(new ArrayList<MenuVO>());
//                    }
//                    menuVO.getMenusvo().add(m);
//                }
//            }
//        }
        return  res;
    }

    /**
     * 获取最高层级编码
     * @param list
     * @return
     */
    private int getMinLenthLevelCode(List<MenuVO> list){
        int res = 0;
//        for (MenuVO menuVO: list){
//            int length = menuVO.getLevelcode().length();
//            if (res == 0){
//                res = length;
//            }else if (length < res) {
//                res = length;
//            }
//        }
        return res;
    }
}


