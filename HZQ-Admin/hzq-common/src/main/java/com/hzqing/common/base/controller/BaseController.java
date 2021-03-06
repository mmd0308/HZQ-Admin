package com.hzqing.common.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzqing.common.response.ResponseMessage;

import java.util.List;

public class BaseController {

    /**
     *  设置请求分页数据
     * @param pageNum 当前页码
     * @param pageSize 每页数据大小
     */
    protected void startPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum==0?1:pageNum, pageSize==0?10:pageSize);
    }

    /**
     * 返回带分页的数据
     * @param datas 返回的list数据
     * @return
     */
    protected ResponseMessage successPage(List<?> datas) {
        return new ResponseMessage().successPage(datas,new PageInfo(datas).getTotal());
    }

    /**
     *  返回成功数据
     * @param datas
     * @return
     */
    protected ResponseMessage success(Object datas) {
        return new ResponseMessage().success(datas);
    }

    /**
     * 请求成功
     * @return
     */
    protected ResponseMessage success() {
        return new ResponseMessage().success();
    }

    /**
     * 请求失败
     * @param msg
     * @return
     */
    protected ResponseMessage error(String msg){
        return new ResponseMessage().error(msg);
    }

}
