package hzqing.com.admin.controller.system;


import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.service.system.IMenuService;
import hzqing.com.admin.utils.ResponseMessage;
import hzqing.com.admin.vo.system.MenuVO;
import hzqing.com.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController<IMenuService,Menu> {
    @GetMapping("/tree/{id}")
    public ResponseMessage<List<MenuVO>> getTree(@PathVariable String id){
        List<MenuVO> menus = baseService.getTree(id);
        return new ResponseMessage<>().success(menus);
    }

    /**
     * 根据角色获取所有资源id,菜单id和按钮id
     * @param roleId
     * @return
     */
    @GetMapping("/getResIdByRoleId/{roleId}")
    public ResponseMessage<Map<String,List<String>>> getResIdByRoleId(@PathVariable String roleId){
        Map<String,List<String>> res = baseService.getResIdByRoleId(roleId);
        return  new ResponseMessage<>().success(res);
    }
}
