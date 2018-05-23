package hzqing.com.common.util;

import java.util.UUID;

public class UUIDUtils {

    public static  String get32UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


}
