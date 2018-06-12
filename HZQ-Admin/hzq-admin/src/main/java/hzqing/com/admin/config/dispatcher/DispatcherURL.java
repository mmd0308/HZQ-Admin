package hzqing.com.admin.config.dispatcher;

import hzqing.com.admin.utils.ResponseMessage;
import hzqing.com.common.constant.Constant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dispatcher")
public class DispatcherURL {

    /**
     * token不存在
     * @return
     */
    @RequestMapping("/noToken")
    public ResponseMessage noToken(){
        return new ResponseMessage().message(Constant.WEB_RESPONSE_STATUES_NO_TOKNE,"请先进行登录!");
    }

    @RequestMapping("/loseToken")
    public ResponseMessage loseToken(){
        return new ResponseMessage().message(Constant.WEB_RESPONSE_STATUES_LOSE_TOKNE,"Token失效，请重新登录!");
    }
    @RequestMapping("/noAuth")
    public ResponseMessage noAuth(){
        return new ResponseMessage().message(Constant.WEB_RESPONSE_STATUES_NO_AUTH,"对不起，你没有该操作权限，请联系管理员进行授权！");
    }



}
