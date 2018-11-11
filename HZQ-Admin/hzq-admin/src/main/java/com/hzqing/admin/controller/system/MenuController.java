package com.hzqing.admin.controller.system;

import com.github.pagehelper.PageInfo;
import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.system.domain.Menu;
import com.hzqing.system.service.IMenuService;
import com.hzqing.system.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description 菜单表
 * @author hzqing
 */
@RestController
@RequestMapping("/hzq/system/menu")
public class MenuController extends BaseController{
    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单表的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<Menu>> list(int pageNum,int pageSize,Menu menu){
        startPage(pageNum,pageSize);
        List<Menu> menus = menuService.selectTableList(menu);
        return successPage(menus);
    }

    /**
     * 获取菜单树行数据
     * @return
     */
    @GetMapping("/tree")
    public ResponseMessage<List<MenuVO>> getTree(){
        List<MenuVO> menus = menuService.selectMenuTree();
        return success(menus);
    }

    /**
    * 新增菜单表信息
    * @param menu
    * @return
    */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  Menu menu) {
        int res = menuService.insertMenu(menu);
        return success(res);
    }

    /**
    * 根据Menu Id修改菜单表
    * @param menuId
    * @return
    */
    @GetMapping("/edit/{menuId}")
    public ResponseMessage<Menu> edit(@PathVariable String menuId){
        Menu menu = menuService.selectMenuById(menuId);
        return success(menu);
    }

    /**
    * 修改菜单表
    * @param menu
    * @return
    */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody Menu menu) {
        int res = menuService.updateMenu(menu);
        return success(res);
    }
    /**
     * 检验编码是否唯一
     * @param menu
     * @return true 表示唯一 false 表示重复
     */
    @PostMapping("/checkPermission")
    public ResponseMessage checkPermission(@RequestBody Menu menu) {
        return success(menuService.checkPermission(menu));
    }


    /**
    * 删除菜单表
    * @param menuIds 多个ID,使用'，'分割
    * @return
    */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String menuIds) {
        int res = menuService.deleteMenuById(menuIds);
        if (res == -1)
            return error("菜单有包含子集菜单或者角色中配置该菜单，不能删除！");
        return success();
    }

}
