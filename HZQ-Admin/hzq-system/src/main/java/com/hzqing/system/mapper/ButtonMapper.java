package com.hzqing.system.mapper;

import com.hzqing.system.domain.Button;

import java.util.List;
/**
 *
 * @description 按钮表
 * @author hzqing
 */
public interface ButtonMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<Button> selectTableList(Button button);
    /**
     * 新增 Button
     * @param button
     * @return
     */
    int insertButton(Button button);

    /**
    * 根据buttonId获取用户信息
    * @param buttonId
    * @return 返回Button
    */
    Button selectButtonById(String buttonId);

    /**
    * 根据buttonId批量删除Button
    * @param buttonId 用户Id数组
    * @return 返回影响行
    */
    int deleteButtonByIds(String buttonId);

    /**
    * 修改Button
    * @param button
    * @return 返回影响行数
    */
    int updateButton(Button button);

    /**
     * 按钮唯一编码校验
     * @param button
     * @return
     */
    List<Button> checkPermission(Button button);
}
