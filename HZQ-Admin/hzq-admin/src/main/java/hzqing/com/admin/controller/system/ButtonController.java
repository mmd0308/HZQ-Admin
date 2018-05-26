package hzqing.com.admin.controller.system;

import hzqing.com.admin.entity.system.Button;
import hzqing.com.admin.service.system.IButtonService;
import hzqing.com.admin.utils.ResponseMessage;
import hzqing.com.admin.vo.system.ButtonVO;
import hzqing.com.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/button")
public class ButtonController extends BaseController<IButtonService,Button> {

    @GetMapping("/getButtonByMenuIdOrRoleId/{id}")
    public ResponseMessage<List<Button>> getButtonByMenuIdOrRoleId(@PathVariable String id, ButtonVO buttonVO) {
        List<Button> buttons = baseService.getButtonByMenuIdOrRoleId(buttonVO);
        return new ResponseMessage<>().success(buttons);
    }
}
