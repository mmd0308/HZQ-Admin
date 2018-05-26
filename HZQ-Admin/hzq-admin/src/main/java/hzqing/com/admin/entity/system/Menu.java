package hzqing.com.admin.entity.system;

import hzqing.com.admin.utils.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "sys_menu")
public class Menu {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     * 标题
     */
    @Column(name = "menu_name")
    private String menuName;

    private String code;

    /**
     * 父级节点
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 资源路径
     */
    private String href;

    /**
     * 前端组件
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单类型 数据字典
     */
    @Column(name = "menu_type")
    private String menuType;

    /**
     * 描述
     */
    private String note;

    /**
     * 0 禁用 1启用
     */
    private String enabled;

    /**
     * 排序
     */
    private Integer sort;

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
     * 获取标题
     *
     * @return menu_name - 标题
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置标题
     *
     * @param menuName 标题
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取父级节点
     *
     * @return parent_id - 父级节点
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级节点
     *
     * @param parentId 父级节点
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取资源路径
     *
     * @return href - 资源路径
     */
    public String getHref() {
        return href;
    }

    /**
     * 设置资源路径
     *
     * @param href 资源路径
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 获取前端组件
     *
     * @return component - 前端组件
     */
    public String getComponent() {
        return component;
    }

    /**
     * 设置前端组件
     *
     * @param component 前端组件
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取菜单类型 数据字典
     *
     * @return menu_type - 菜单类型 数据字典
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型 数据字典
     *
     * @param menuType 菜单类型 数据字典
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取描述
     *
     * @return note - 描述
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置描述
     *
     * @param note 描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取0 禁用 1启用
     *
     * @return enabled - 0 禁用 1启用
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * 设置0 禁用 1启用
     *
     * @param enabled 0 禁用 1启用
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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