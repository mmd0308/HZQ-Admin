package hzqing.com.admin.service.system.impl;


import hzqing.com.admin.base.service.impl.BaseServiceImpl;
import hzqing.com.admin.entity.system.Button;
import hzqing.com.admin.service.system.IButtonService;
import hzqing.com.admin.vo.system.ButtonVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ButtonServiceImpl extends BaseServiceImpl<Button> implements IButtonService {

    public ButtonServiceImpl() {
        super.mapper= "ButtonMapper";
    }

    @Override
    public List<Button> getButtonByMenuIdOrRoleId(ButtonVO buttonVO) {
        return (List<Button>) baseDao.findForList(mapper+".getButtonByMenuIdOrRoleId",buttonVO);
    }
}
