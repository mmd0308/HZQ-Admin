package hzqing.com.common.base.service;


import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBaseService<T> {
    Integer save(T t);

    T getById(String id);

    int update(T t);

    PageInfo<T> queryPage(Integer page, Integer pageSize, T t);

    int deletedById(String id);

    List<T> findAll(T t);

    boolean checkCode(String code, String id);

}
