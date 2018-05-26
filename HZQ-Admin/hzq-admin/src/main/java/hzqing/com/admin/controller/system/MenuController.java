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

@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController<IMenuService,Menu> {
    @GetMapping("/tree/{id}")
    public ResponseMessage<List<MenuVO>> getTree(@PathVariable String id){
    //    List<MenuVO> menus = baseService.getTree(id);
        return new ResponseMessage<>().success(null);
    }



    /**
     * 根据权限，父级别编码，拉去菜单
     * @param auth  权限编码
     * @param code  父级编码
     * @return
     */
    @GetMapping("/show/getDefaultMenus/{auth}/{code}")
    public ResponseMessage<List<MenuVO>> getDefaultMenus(@PathVariable String auth, @PathVariable String code){
     //   return new ResponseMessage<>().success(baseService.getMenusByAuthAndCode(auth,code));
        return null;
    }
}
