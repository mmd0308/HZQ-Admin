package hzqing.com.admin.config.dispatcher;

import hzqing.com.admin.constant.Constant;
import hzqing.com.admin.utils.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dispatcher")
public class DispatcherURL {

    /**
     * token不存在
     * @return
     */
    @GetMapping("/noToken")
    public ResponseMessage noToken(){
        return new ResponseMessage().message(Constant.WEB_RESPONSE_STATUES_NO_TOKNE,"请先进行登录!");
    }


    @GetMapping("/loseToken")
    public ResponseMessage loseToken(){
        return new ResponseMessage().message(Constant.WEB_RESPONSE_STATUES_LOSE_TOKNE,"Token失效，请重新登录!");
    }
}
