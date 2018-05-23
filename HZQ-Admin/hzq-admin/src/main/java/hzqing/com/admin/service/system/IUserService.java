package hzqing.com.admin.service.system;


import hzqing.com.admin.base.service.IBaseService;
import hzqing.com.admin.entity.system.User;
import hzqing.com.admin.vo.system.UserVo;

import java.util.HashMap;

public interface IUserService extends IBaseService<User> {
    String login(String username, String password);

    UserVo getUserinfo(String userId);

    void saveUserRole(HashMap<String, Object> map);

    void logout(String token);

    User getUserByUName(String username);
}
