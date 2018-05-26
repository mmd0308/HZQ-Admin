package hzqing.com.common.base.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hzqing.com.common.base.mapper.BaseMapper;
import hzqing.com.common.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;


public class BaseServiceImpl<M extends BaseMapper<T>,T> implements IBaseService<T> {
    @Autowired
    protected M mapper;
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
    public boolean checkCode(String code, String id) {
        Example example = new Example((Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        if (id != null) {
            criteria.andNotEqualTo("id",id);
        }
        List<T> res = mapper.selectByExample(example);
        if (res.size() == 0 )
            return true;
        return false;
    }

    @Override
    public  T getById(String id) {
        return mapper.selectByPrimaryKey(id);
    }


}
