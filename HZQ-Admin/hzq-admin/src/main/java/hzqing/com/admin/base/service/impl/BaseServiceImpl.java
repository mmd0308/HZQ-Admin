package hzqing.com.admin.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hzqing.com.admin.base.dao.IBaseDao;
import hzqing.com.admin.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    protected IBaseDao baseDao;

    public String mapper;

    @Override
    public int save(T t) {
        return (int) baseDao.save(mapper+".save",t);
    }

    @Override
    public T getById(String id) {
        return (T) baseDao.selectById(mapper+".selectById",id);
    }

    @Override
    public PageInfo<T> queryPage(Integer start, Integer pageSize, T t) {
        if (null == start){
            start = 1;
        }
        if (null == pageSize){
            pageSize = 10;
        }
        PageHelper.startPage(start,pageSize);
        return new PageInfo<T>(findAll(t));
    }

    @Override
    public List<T> findAll(T t) {
        return (List<T>) baseDao.findForList(mapper+".query",t);
    }

    @Override
    public int update(T t) {
        return (int) baseDao.update(mapper+".update",t);
    }

    @Override
    public int deletedById(String id) {
        return (int) baseDao.delete(mapper+".deletedById",id);
    }

    @Override
    public boolean checkCode(String code, String id) {
        Map<String,String> params = new HashMap<>();
        params.put("code",code);
        params.put("id",id);
        List<T> res = (List<T>) baseDao.findForList(mapper + ".checkCode", params);
        return res.size() == 0 ? true : false;
    }


    public List<T> list(T t){
        return  null;
    }

}
