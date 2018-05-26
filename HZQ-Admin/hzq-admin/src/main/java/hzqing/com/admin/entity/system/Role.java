package hzqing.com.admin.entity.system;

import hzqing.com.admin.utils.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role")
public class Role {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    private String code;

    @Column(name = "role_name")
    private String roleName;

    /**
     * 0禁用 1启用
     */
    private String enabled;

    private String note;

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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取0禁用 1启用
     *
     * @return enabled - 0禁用 1启用
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * 设置0禁用 1启用
     *
     * @param enabled 0禁用 1启用
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
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