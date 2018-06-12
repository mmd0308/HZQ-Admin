package hzqing.com.admin.mapper.system;

import hzqing.com.admin.entity.system.Button;
import hzqing.com.common.base.mapper.BaseMapper;

import java.util.List;

public interface ButtonMapper extends BaseMapper<Button> {
    List<String> getResPathByRoleIds(String roleIds);
}