package hzqing.com.admin.entity.system;

import hzqing.com.admin.utils.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "sys_button")
public class Button {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     * 按钮编码
     */
    private String code;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 关联菜单
     */
    @Column(name = "menu_id")
    private String menuId;

    private String enabled;

    /**
     * 保留字段
     */
    private String attr1;

    /**
     * 保留字段
     */
    private String attr2;

    /**
     * 保留字段
     */
    private String attr3;

    /**
     * 保留字段
     */
    private String attr4;

    /**
     * 保留字段
     */
    private String attr5;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取按钮编码
     *
     * @return code - 按钮编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置按钮编码
     *
     * @param code 按钮编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取按钮名称
     *
     * @return name - 按钮名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置按钮名称
     *
     * @param name 按钮名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取关联菜单
     *
     * @return menu_id - 关联菜单
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置关联菜单
     *
     * @param menuId 关联菜单
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * @return enabled
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取保留字段
     *
     * @return attr1 - 保留字段
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * 设置保留字段
     *
     * @param attr1 保留字段
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * 获取保留字段
     *
     * @return attr2 - 保留字段
     */
    public String getAttr2() {
        return attr2;
    }

    /**
     * 设置保留字段
     *
     * @param attr2 保留字段
     */
    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    /**
     * 获取保留字段
     *
     * @return attr3 - 保留字段
     */
    public String getAttr3() {
        return attr3;
    }

    /**
     * 设置保留字段
     *
     * @param attr3 保留字段
     */
    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    /**
     * 获取保留字段
     *
     * @return attr4 - 保留字段
     */
    public String getAttr4() {
        return attr4;
    }

    /**
     * 设置保留字段
     *
     * @param attr4 保留字段
     */
    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    /**
     * 获取保留字段
     *
     * @return attr5 - 保留字段
     */
    public String getAttr5() {
        return attr5;
    }

    /**
     * 设置保留字段
     *
     * @param attr5 保留字段
     */
    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }
}