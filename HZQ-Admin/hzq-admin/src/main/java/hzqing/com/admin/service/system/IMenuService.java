package hzqing.com.admin.service.system;

import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.vo.system.MenuVO;
import hzqing.com.common.base.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IMenuService extends IBaseService<Menu> {
    /**
     * 根据父id获取tree
     * @param id
     * @return
     */
    List<MenuVO> getTree(String id);

    /**
     * 根据父级别的id,获取子类下一个levelcode
     * @param parentId
     * @return
     */
    String getNextLevelCode(String parentId, String levelCode);

    /**
     * 根据角色id,获取菜单信息
     * @param role
     * @return
     */
    List<MenuVO> getMenusByRids(Map<String, String> role, String id);

    /**
     * 根据用户id，获取菜单
     * @param id
     * @return
     */
    List<Menu> getMenusByUid(String id);

    List<MenuVO> getDefaultMenus();

    List<MenuVO> getMenusVoTree(MenuVO menuVO);

    List<MenuVO> getMenusByAuthAndCode(String auth, String code);

    public Menu getMenuByCode(String code);

    List<MenuVO> getListMenusByRids(Map<String, String> maps);
}
