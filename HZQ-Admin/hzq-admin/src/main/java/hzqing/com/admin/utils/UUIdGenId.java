package hzqing.com.admin.utils;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
