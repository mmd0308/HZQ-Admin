package hzqing.com.admin.entity.system;

import hzqing.com.admin.utils.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "sys_dict")
public class Dict {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     *  
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 字典类型，系统字典 SYS(系统字典不可以删除） 业务字典 BUS
     */
    @Column(name = "dict_type")
    private String dictType;

    /**
     * 字典编码 唯一
     */
    private String code;

    /**
     * 1启用。 0禁用
     */
    private String enabled;

    /**
     * 描述
     */
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

    /**
     * 获取 
     *
     * @return dict_name -  
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置 
     *
     * @param dictName  
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取父级id
     *
     * @return parent_id - 父级id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级id
     *
     * @param parentId 父级id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取字典类型，系统字典 SYS(系统字典不可以删除） 业务字典 BUS
     *
     * @return dict_type - 字典类型，系统字典 SYS(系统字典不可以删除） 业务字典 BUS
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 设置字典类型，系统字典 SYS(系统字典不可以删除） 业务字典 BUS
     *
     * @param dictType 字典类型，系统字典 SYS(系统字典不可以删除） 业务字典 BUS
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * 获取字典编码 唯一
     *
     * @return code - 字典编码 唯一
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字典编码 唯一
     *
     * @param code 字典编码 唯一
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取1启用。 0禁用
     *
     * @return enabled - 1启用。 0禁用
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * 设置1启用。 0禁用
     *
     * @param enabled 1启用。 0禁用
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
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