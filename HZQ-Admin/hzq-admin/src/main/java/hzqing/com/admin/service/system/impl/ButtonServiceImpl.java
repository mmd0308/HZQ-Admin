package hzqing.com.admin.service.system.impl;


import hzqing.com.admin.entity.system.Button;
import hzqing.com.admin.mapper.system.ButtonMapper;
import hzqing.com.admin.service.system.IButtonService;
import hzqing.com.admin.vo.system.ButtonVO;
import hzqing.com.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ButtonServiceImpl extends BaseServiceImpl<ButtonMapper,Button> implements IButtonService {


    @Override
    public List<Button> getButtonByMenuIdOrRoleId(ButtonVO buttonVO) {
        Button button = new Button();
        button.setMenuId(buttonVO.getMenuId());
        List<Button> buttons = this.findAll(button);
        return buttons;
    }
}
