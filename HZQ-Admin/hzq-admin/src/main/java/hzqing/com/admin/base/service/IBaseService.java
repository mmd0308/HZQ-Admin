package hzqing.com.admin.base.service;


import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBaseService<T> {

    int save(T t);

     T getById(String id);

     PageInfo<T> queryPage(Integer start, Integer pageSize, T t);

     List<T> findAll(T t);

    int update(T t);

    int deletedById(String id);

    boolean checkCode(String code,String id);
}
