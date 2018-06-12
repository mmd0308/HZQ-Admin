package hzqing.com.admin.mapper.system;

import hzqing.com.admin.entity.system.User;
import hzqing.com.common.base.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    void deleteUserRoleByUserId(String userId);

    void batchSave(List<Map<String,String>> lists);
}