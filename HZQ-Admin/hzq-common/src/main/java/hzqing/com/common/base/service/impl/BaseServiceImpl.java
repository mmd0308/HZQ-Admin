package hzqing.com.common.base.service.impl;


import hzqing.com.common.base.mapper.BaseMapper;
import hzqing.com.common.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BaseServiceImpl<M extends BaseMapper<T>,T> implements IBaseService<T> {
    @Autowired
    private M mapper;
    @Override
    public String find() {
        List<T> ts = mapper.selectAll();
        System.out.println(ts);
        return "server";
    }
//    @Autowired
//    public M mapper;
//
//    public void findAll(){
//        this.mapper.selectAll();
//        this.mapper.faa();
//    }

}
