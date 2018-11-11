package com.hzqing.system.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.Button;
import com.hzqing.system.domain.Menu;
import com.hzqing.system.mapper.ButtonMapper;
import com.hzqing.system.service.IButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 *
 * @description 按钮表
 * @author hzqing
 */
@Service
public class ButtonServiceImpl implements IButtonService {
    @Autowired
    private ButtonMapper buttonMapper;

    @Override
    public boolean checkPermission(Button button) {
        List<Button> buttons = buttonMapper.checkPermission(button);
        if (buttons.size() == 0 )
            return true;
        return false;
    }

    /**
     * 根据条件检索列表
     * @param button
     * @return
     */
    public List<Button> selectTableList(Button button) {
        return buttonMapper.selectTableList(button);
    }

    /**
     * 新增Button
     * @param button
     * @return
     */
    public int insertButton(Button button) {
        button.setButId(UUIDUtils.get32UUID());
        button.setCreateTime(new Date());
        button.setUpdateTime(new Date());
        return buttonMapper.insertButton(button);
    }

    /**
    * 根据buttonId获取信息
    * @param buttonId
    * @return
    */
    public Button selectButtonById(String buttonId) {
        return buttonMapper.selectButtonById(buttonId);
    }
    /**
     * 根据buttonId批量删除Button
     * @param ids Button Id数组
     * @return 返回影响行
     */
    public int deleteButtonByIds(String ids) {
        return buttonMapper.deleteButtonByIds(ids);
    }
    /**
     * 修改Button
     * @param button
     * @return 返回影响行数
     */
    public int updateButton(Button button) {
        return buttonMapper.updateButton(button);
    }
}
