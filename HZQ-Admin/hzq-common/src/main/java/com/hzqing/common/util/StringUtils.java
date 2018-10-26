package com.hzqing.common.util;

public class StringUtils {

    /**
     * 将下划线命名方式改为驼峰方式
     * @param name
     * @return
     */
    public static String convertToCamelCase(String name) {
        if (null == name || name == "")
            return "";
        // 避免数据库名称是SYS_USER 大写方式
        name = name.toLowerCase();
        // 如果不包含下划线分割，首字母大写返回
        if (!name.contains("_")) {
            return name.substring(0,1).toUpperCase()+ name.substring(1);
        }
        // 有下划线分割的
        StringBuilder res = new StringBuilder();
        String[] names = name.split("_");
        for (String item: names) {
            if (item.isEmpty()) {
                continue;
            }
            res.append(item.substring(0,1).toUpperCase());
            res.append( item.substring(1));
        }
        return res.toString();
    }

}
