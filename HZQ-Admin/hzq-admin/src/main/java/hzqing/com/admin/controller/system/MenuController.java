package hzqing.com.admin.controller.system;


import hzqing.com.admin.base.controller.BaseController;
import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.service.system.IMenuService;
import hzqing.com.admin.utils.ResponseMessage;
import hzqing.com.admin.vo.system.MenuVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController<Menu,IMenuService> {
    @GetMapping("/tree/{id}")
    public ResponseMessage<List<MenuVO>> getTree(@PathVariable String id){
        List<MenuVO> menus = baseService.getTree(id);
        return new ResponseMessage<>().success(menus);
    }

    /**
     * 根据父级别的id,获取子类下一个levelcode
     * @param parentId
     * @return
     */
    @GetMapping("/getNextLevelCode/{parentId}/{levelCode}")
    public ResponseMessage<String> getNextLevelCode(@PathVariable String parentId,@PathVariable String levelCode){
        String resCode = baseService.getNextLevelCode(parentId,levelCode);
        return  new ResponseMessage<>().success(resCode);
    }

    /**
     * 根据权限，父级别编码，拉去菜单
     * @param auth  权限编码
     * @param code  父级编码
     * @return
     */
    @GetMapping("/show/getDefaultMenus/{auth}/{code}")
    public ResponseMessage<List<MenuVO>> getDefaultMenus(@PathVariable String auth, @PathVariable String code){
        return new ResponseMessage<>().success(baseService.getMenusByAuthAndCode(auth,code));
    }
}
