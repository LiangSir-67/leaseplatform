package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.ExceptionUtil;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.interceptors.ReleaseToken;
import cn.leaseplatform.mapper.PersonalOrdersMapper;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import cn.leaseplatform.vo.UserPersonalOrderVo;
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


    @ApiOperation(value = "添加用户个人订单")
    @GetMapping("/addUserPersonalOrders")
    public R addUserPersonalOrders(UserPersonalOrderVo userPersonalOrderVo, HttpServletRequest request){
        userPersonalOrderVo.toString();
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            userPersonalOrderVo.setUserId(Long.valueOf(userId));
            int result = personalOrdersMapper.insert(userPersonalOrderVo);
            return R.ok().data("result",result).message("添加成功！");
        }catch (Exception e){
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
        }
        return R.error().message("添加失败！");
    }

    @ApiOperation(value = "获取用户个人订单")
    @GetMapping("/getUserPersonalOrders")
    public R getUserPersonalOrders(@RequestParam(defaultValue = "1") Integer currentPage,HttpServletRequest request){
        Page<UserPersonalOrderVo> page = new Page<>(currentPage,10);
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            IPage<UserPersonalOrderVo> userPersonalOrderVoIPage = personalOrdersMapper.getUserPersonalOrders(page, Integer.valueOf(userId));
            return R.ok().data("userPersonalOrderVoIPage",userPersonalOrderVoIPage).message("获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
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
