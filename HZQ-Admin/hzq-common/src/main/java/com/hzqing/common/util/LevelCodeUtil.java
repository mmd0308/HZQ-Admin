package com.hzqing.common.util;

public class LevelCodeUtil {
    /**
     * 获取下一个levelCode
     * @param lCode 同级别最大的levelcode
     * @param  levelCode 父id自己的levelCode
     * @return
     */
    public static  String getNextLevelCode(String lCode,String levelCode){
        if(null != lCode && lCode != ""){
            lCode = Integer.parseInt(lCode.substring(lCode.length()-6))+1+"";
            String str = "";
            int num = 6-lCode.length()%6;
            for (int i=0;i<num;i++){
                str = str + "0";
            }
            lCode = str + lCode;
        }else {
            lCode = "000001";
        }
        return  levelCode+lCode;
    }
}
