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

    List<Menu> getListMenusByRids(String rids);

    Map<String,List<String>> getResIdByRoleId(String roleId);

    List<String> getResCodeByRoleIds(String roleIds);
}
