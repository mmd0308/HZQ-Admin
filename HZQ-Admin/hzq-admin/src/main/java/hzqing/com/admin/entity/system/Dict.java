package hzqing.com.admin.entity.system;

import org.apache.ibatis.type.Alias;

@Alias("dict")
public class Dict {
    private String id;
    private String dictName;
    private String parentId;
    private String dictType;
    private String code;
    private String enabled;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Dict{" +
                "id='" + id + '\'' +
                ", dictName='" + dictName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", dictType='" + dictType + '\'' +
                ", code='" + code + '\'' +
                ", enabled='" + enabled + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
