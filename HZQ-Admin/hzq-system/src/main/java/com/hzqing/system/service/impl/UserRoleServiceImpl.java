package com.hzqing.system.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.UserRole;
import com.hzqing.system.mapper.UserRoleMapper;
import com.hzqing.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @description 用户角色关系表
 * @author hzqing.com
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    private UserRoleMapper userroleMapper;
    /**
     * 根据条件检索列表
     * @param userrole
     * @return
     */
    public List<UserRole> selectTableList(UserRole userrole) {
        return userroleMapper.selectTableList();
    }

    /**
     * 新增UserRole
     * @param userrole
     * @return
     */
    public int insertUserRole(UserRole userrole) {
        userrole.setId(UUIDUtils.get32UUID());
        return userroleMapper.insertUserRole(userrole);
    }

    /**
    * 根据userroleId获取信息
    * @param userroleId
    * @return
    */
    public UserRole selectUserRoleById(String userroleId) {
        return userroleMapper.selectUserRoleById(userroleId);
    }
    /**
     * 根据userroleId批量删除UserRole
     * @param ids UserRole Id数组
     * @return 返回影响行
     */
    public int deleteUserRoleByIds(String ids) {
        String[] userroleIds = ids.split(",");
        return userroleMapper.deleteUserRoleByIds(userroleIds);
    }
    /**
     * 修改UserRole
     * @param userrole
     * @return 返回影响行数
     */
    public int updateUserRole(UserRole userrole) {
        return userroleMapper.updateUserRole(userrole);
    }
}
