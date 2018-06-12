package hzqing.com.admin.vo.system;

import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.entity.system.Role;
import hzqing.com.admin.entity.system.User;

import java.io.Serializable;
import java.util.List;

public class UserVo extends User implements Serializable {


    /**
     * 角色
     */
    private List<Role> roles;

    /**
     * 后台权限菜单
     */
    private List<Menu> menus;
    /**
     * 资源的编码
     */
    private List<String> resCode;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getResCode() {
        return resCode;
    }

    public void setResCode(List<String> resCode) {
        this.resCode = resCode;
    }
}
