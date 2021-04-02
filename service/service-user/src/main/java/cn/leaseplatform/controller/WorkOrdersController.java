package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.ExceptionUtil;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.mapper.WorkOrdersMapper;
import cn.leaseplatform.service.WorkOrdersService;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import cn.leaseplatform.vo.UserWorkOrdersVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
@Api(tags = "用户工单服务")
@RestController
@RequestMapping("/service-user/work-orders")
@Slf4j
public class WorkOrdersController {

    @Autowired
    private WorkOrdersMapper workOrdersMapper;

    @Autowired
    private WorkOrdersService workOrdersService;

    @ApiOperation(value = "获取我的工单列表")
    @GetMapping("/getUserWorkOrders")
    public R getUserWorkOrders(@RequestParam(defaultValue = "1") Integer currentPage, HttpServletRequest request){
        Page<UserWorkOrdersVo> page = new Page<>(currentPage, 10);
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            IPage<UserWorkOrdersVo> userWorkOrdersIPage = workOrdersService.getUserWorkOrders(page, Integer.valueOf(userId));
            return R.ok().data("userWorkOrdersIPage",userWorkOrdersIPage).message("获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("获取失败！");
        }
    }
}
