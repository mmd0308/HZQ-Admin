package hzqing.com.admin.vo.system;

import hzqing.com.admin.entity.system.Button;
import org.apache.ibatis.type.Alias;

@Alias("buttonvo")
public class ButtonVO extends Button {
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
