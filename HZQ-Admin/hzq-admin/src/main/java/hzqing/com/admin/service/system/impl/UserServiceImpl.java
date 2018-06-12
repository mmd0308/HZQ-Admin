package hzqing.com.admin.service.system.impl;

import hzqing.com.admin.entity.system.Button;
import hzqing.com.admin.entity.system.Menu;
import hzqing.com.admin.entity.system.Role;
import hzqing.com.admin.entity.system.User;
import hzqing.com.admin.mapper.system.UserMapper;
import hzqing.com.admin.service.system.IButtonService;
import hzqing.com.admin.service.system.IMenuService;
import hzqing.com.admin.service.system.IRoleService;
import hzqing.com.admin.service.system.IUserService;
import hzqing.com.admin.vo.system.UserVo;
import hzqing.com.common.base.service.impl.BaseServiceImpl;
import hzqing.com.common.constant.Constant;
import hzqing.com.common.jwt.JwtTokenUtil;
import hzqing.com.common.util.AESUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IButtonService buttonService;

    public String login(String username,String password){
        User param = new User();
        param.setUsername(username);
        User user = this.mapper.selectOne(param);
        System.out.println(user);
        if (user.getId() == null || user.getId() == "") {
            return null;
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        String token = null;
        if (user.getPassword().equals(password)) {
            //生成jwt
            Map<String, Object> claims = new HashMap<String, Object>();  // Claims包含您想要签署的任何信息
            String iss = AESUtil.AESEncode(Constant.AES_SECRET,user.getId()+","+user.getUsername()+","+user.getPassword());
            claims.put("iss",iss); //jwt的签发者 保存用户的帐号和密码以及id 使用AES对称加密
            claims.put("sub",user.getUsername()); // JWT所面向的用户 用户的昵称
            claims.put("iat", new Date());
            claims.put("jti",UUID.randomUUID()); //jwt的唯一身份表示
            //获取token
            token = JwtTokenUtil.generateToken(claims, Constant.JWT_SECRET, Constant.JWT_EXPIRATION);
        }
        return token;
    }

    /**
     * 根据条件查询用户
     * @param user
     * @return
     */
    private User getLoginUser(User user) {
//        return (User) baseDao.findForObject(mapper + ".getLoginUser", user);
        return null;
    }

    /**
     * 根据token获取用户信息
     * @param userId
     * @return
     */
    @Override
    public UserVo getUserinfo(String userId) {
        User user = this.getById(userId);
        UserVo uservo = new UserVo();
        BeanUtils.copyProperties(user,uservo);
        if (uservo.getUsername().equals("admin")) { // 账号为admin的拥有所有的菜单和资源权限
            uservo.setMenus(menuService.findAll(new Menu()));
            List<Button> buttons = buttonService.findAll(new Button());
            List<String> codes = new ArrayList<>();
            buttons.forEach(button -> {
                codes.add(button.getCode());
            });
            uservo.setResCode(codes);
        }else { // 非admin用户
            // 根据用户的id获取角色
            List<Role> rols = roleService.getRoleByUserId(userId);
            if (rols.size() > 0) {
                //设置用户的角色
                uservo.setRoles(rols);
                // 获取菜单
                StringBuffer buffer = new StringBuffer();
                rols.forEach(role -> {
                    buffer.append(role.getId());
                    buffer.append(",");
                });
                String roleIds = buffer.toString().substring(0,buffer.toString().length()-1);
                List<Menu> menus = menuService.getListMenusByRids(roleIds);
                uservo.setMenus(menus);
                //  索取所有的按钮资源编码根据角色id
                List<String> buttons = menuService.getResCodeByRoleIds(roleIds);
                uservo.setResCode(buttons);
            }
        }
        return uservo;
    }



    /**
     * 根据用户名获取用户信息
     * @param
     * @return
     */
    @Override
    public User getUserByUName(String username){
        User user = new User();
        user.setUsername(username);
        User one = this.mapper.selectOne(user);
        return one;
    }

    @Override
    @Transactional
    public void saveUserRole(HashMap<String, Object> map) {
        String userId  = map.get("userId").toString();
        ArrayList<HashMap<String,Object>> roles = (ArrayList<HashMap<String, Object>>) map.get("roles");
        List<Map<String,String>> lists = new ArrayList<>();
        for (HashMap r : roles){
            HashMap<String,String> m = new HashMap<>();
            m.put("user_id",userId);
            m.put("role_id", (String) r.get("id"));
            lists.add(m);
        }
        //删除原先的
        mapper.deleteUserRoleByUserId(userId);
        if (lists.size()>0){
            //批量新增新的权限
            mapper.batchSave(lists);
        }

    }

    @Override
    public void logout(String token) {
        String username = JwtTokenUtil.getUsernameFromToken(token, Constant.JWT_SECRET);
    }



//    @Override
//    public int save(User user) {
//        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
//        return super.save(user);
//    }


}
