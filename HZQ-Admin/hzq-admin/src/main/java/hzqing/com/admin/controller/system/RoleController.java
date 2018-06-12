package hzqing.com.admin.controller.system;


import hzqing.com.admin.entity.system.Role;
import hzqing.com.admin.service.system.IRoleService;
import hzqing.com.admin.utils.ResponseMessage;
import hzqing.com.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController<IRoleService,Role> {
    /**
     * 获取所有的启用角色
     * @return
     */
    @GetMapping("/enabled")
    public ResponseMessage<List<Role>> getAllEnabledRole(){
        List<Role> enabledRole = baseService.getAllEnabledRole();
        return new ResponseMessage<List<Role>>().success(enabledRole);
    }

    /**
     * 根据用户id,获取用户角色
     * @param id
     * @return
     */
    @GetMapping("/getRoleByUserId/{id}")
    public ResponseMessage<List<Role>> getRoleByUserId(@PathVariable String id){
        List<Role> roles = baseService.getRoleByUserId(id);
        return new ResponseMessage<>().success(roles);
    }

    /**
     * 角色绑定资源
     * @param resouce
     * @return
     */
    @PostMapping("/addRoleRes/{roleId}/auth")
    public ResponseMessage<String> addRoleMenu(@RequestBody HashMap<String,Object> resouce){
        baseService.addRoleRes(resouce);
        return  new ResponseMessage<>().success("success");
    }
}
