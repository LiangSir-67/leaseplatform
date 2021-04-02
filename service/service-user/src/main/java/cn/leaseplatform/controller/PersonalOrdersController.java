package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.ExceptionUtil;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.PersonalOrders;
import cn.leaseplatform.interceptors.ReleaseToken;
import cn.leaseplatform.mapper.PersonalOrdersMapper;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
@Api(tags = "个人订单服务")
@RestController
@RequestMapping("/service-user/personal-orders")
@Slf4j
public class PersonalOrdersController {

    @Autowired
    private PersonalOrdersMapper personalOrdersMapper;

    @ApiOperation(value = "获取用户个人订单")
    @GetMapping("/getUserPersonalOrders")
    public R getUserPersonalOrders(@RequestParam(defaultValue = "1") Integer currentPage,HttpServletRequest request){
        Page<PersonalOrders> page = new Page<>(currentPage, 10);
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            System.out.println("userId=="+userId);
            QueryWrapper<PersonalOrders> personalOrdersQueryWrapper = new QueryWrapper<>();
            personalOrdersQueryWrapper.eq("user_id",userId);
            personalOrdersQueryWrapper.eq("status",1);
            IPage<PersonalOrders> personalOrdersIPage = personalOrdersMapper.selectPage(page, personalOrdersQueryWrapper);
            return R.ok().data("personalOrders",personalOrdersIPage).message("获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error().message("获取失败！");
    }

    /**
     * 一般业务逻辑，这里使用软删除
     * @param orderId
     * @return
     */
    @ApiOperation(value = "用户订单退租")
    @ReleaseToken
    @DeleteMapping("/offRent")
    public R offRent(String orderId){
        try {
            int result = personalOrdersMapper.deleteById(orderId);
            if (result > 0){
                return R.ok().message("退租成功！");
            }else {
                return R.error().message("退租失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
        }
        return R.error().message("退租失败！");
    }

}
