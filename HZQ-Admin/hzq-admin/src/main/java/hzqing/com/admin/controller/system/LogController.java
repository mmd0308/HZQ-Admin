package hzqing.com.admin.controller.system;


import hzqing.com.admin.entity.system.Log;
import hzqing.com.admin.service.system.ILogService;
import hzqing.com.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class LogController extends BaseController<ILogService,Log> {
}
