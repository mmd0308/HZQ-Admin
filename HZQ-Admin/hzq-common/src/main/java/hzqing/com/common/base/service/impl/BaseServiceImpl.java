package hzqing.com.common.base.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hzqing.com.common.base.mapper.BaseMapper;
import hzqing.com.common.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BaseServiceImpl<M extends BaseMapper<T>,T> implements IBaseService<T> {
    @Autowired
    private M mapper;
    @Override
    public Integer save(T t) {
        return mapper.insert(t);
    }

    @Override
    public int update(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public  PageInfo<T> queryPage(Integer start, Integer pageSize, T t) {
        if (null == start){
            start = 1;
        }
        if (null == pageSize){
            pageSize = 10;
        }
        PageHelper.startPage(start,pageSize);
        return new PageInfo<T>( mapper.select(t));
    }

    @Override
    public int deletedById(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<T> findAll(T t) {
        return mapper.select(t);
    }

    @Override
    public  T getById(String id) {
        return mapper.selectByPrimaryKey(id);
    }


}
