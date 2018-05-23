package hzqing.com.admin.service.system;


import hzqing.com.admin.base.service.IBaseService;
import hzqing.com.admin.entity.system.Button;
import hzqing.com.admin.vo.system.ButtonVO;

import java.util.List;

public interface IButtonService extends IBaseService<Button> {
    List<Button> getButtonByMenuIdOrRoleId(ButtonVO buttonVO);
}
