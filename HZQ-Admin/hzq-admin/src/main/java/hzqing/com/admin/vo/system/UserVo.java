package hzqing.com.admin.vo.system;

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
     * 前台权限菜单
     */
    private List<MenuVO> indexMenus;
    /**
     * 后台权限菜单
     */
    private List<MenuVO> adminMenus;
    /**
     * 资源的编码
     */
    private List<String> resCode;

    public List<MenuVO> getIndexMenus() {
        return indexMenus;
    }

    public void setIndexMenus(List<MenuVO> indexMenus) {
        this.indexMenus = indexMenus;
    }

    public List<MenuVO> getAdminMenus() {
        return adminMenus;
    }

    public void setAdminMenus(List<MenuVO> adminMenus) {
        this.adminMenus = adminMenus;
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
