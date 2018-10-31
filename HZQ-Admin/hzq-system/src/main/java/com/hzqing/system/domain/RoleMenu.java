package com.hzqing.system.domain;

import lombok.Data;
/**
 *
 * @description 角色和菜单关系表
 * @author hzqing.com
 */
@Data
public class RoleMenu {

    /**
     * 
     */
    private String id;

    /**
     *  角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

}
