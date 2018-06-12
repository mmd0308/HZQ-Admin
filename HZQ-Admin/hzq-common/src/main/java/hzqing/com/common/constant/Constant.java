package hzqing.com.common.constant;

public class Constant {


    /**
     * 用户的id
     */
    public static final String USER_ID = "USER_ID";

    /**
     * JWT的签名密钥
     */
    public static final String JWT_SECRET = "hzqing.com";
    /**
     * jwt的过期时间  单位秒
     */
    public static final int JWT_EXPIRATION = 1800;

    /**
     * jwt最后跟新时间
     */
    public static final int JWT_REPLACE_TOKEN = 600;
    /**
     * AES对称加密密钥
     */
    public static final String AES_SECRET = "HZQING.COM";

    /**
     * 传递token的key值
     */
    public static final String ACCESS_TOKEN_KEY = "ACCESS-TOKEN";
    /**
     * 跟新token时候的key值
     */
    public static final String REPLACE_TOKEN_KEY = "REPLACE_TOKEN";

    /**
     * 返回成功 状态200
     */
    public static final int WEB_RESPONSE_STATUES_SUCCESS = 200;
    /**
     * 没有权限
     */
    public static final int WEB_RESPONSE_STATUES_NO_AUTH = 308400;
    /**
     * TOKEN 失效
     */
    public static final int WEB_RESPONSE_STATUES_LOSE_TOKNE = 308300;

    /**
     * token不存在
     */
    public static final int WEB_RESPONSE_STATUES_NO_TOKNE = 308200;

    /**
     * 登录失败  登录名或者密码错误  308001
     */
    public static final int WEB_RESPONSE_STATUES_LOGIN_ERROR = 308001;

    /**
     * 提示前端跟新token
     */
    public static final int WEB_RESPONSE_STATUES_REPLACE_TOKEN = 308100;

    /**
     * 404
     */
    public static final int WEB_RESPONSE_STATUES_ERROR = 404;
    /**
     * 博客状态 发布编码
     */
    public static final String BLOG_STATE_FB = "FB";
    /**
     *  博客状态 发布
     */
    public static final String BLOG_STATE_FB_REP = "发布";

    /**
     * 博客状态 草稿编码
     */
    public static final String BLOG_STATE_CG = "CG";
    /**
     *  博客状态 草稿
     */
    public static final String BLOG_STATE_CG_REP = "草稿";

    /**
     * 资源类型，按钮
     */
    public static final String RES_TYPE_BUTTON="button";
    /**
     * 资源类型，菜单
     */
    public static final String RES_TYPE_MENU="menu";


    /**
     * 数据字典类型 业务字典
     */
    public static final String DICT_TYPE_BUS="BUS";
    /**
     * 数据字典类型  系统字典
     */
    public static final String DICT_TYPE_SYS="SYS";
    /**
     * 菜单类型，前段展示权限菜单
     */
    public static final String MENU_INDEX_TYPE = "MENU_TYPE_INDEX_AUTH";
    /**
     * 菜单类型，后台管理权限菜单
     */
    public static final String MENU_ADMIN_TYPE = "MENU_TYPE_ADMIN_AUTH";


}
