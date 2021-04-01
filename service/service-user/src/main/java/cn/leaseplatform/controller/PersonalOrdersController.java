package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.PersonalOrders;
import cn.leaseplatform.mapper.PersonalOrdersMapper;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
@RequestMapping("/personal-orders")
@Slf4j
public class PersonalOrdersController {

    @Autowired
    private PersonalOrdersMapper personalOrdersMapper;

    @ApiModelProperty(value = "获取用户个人订单")
    @GetMapping("/getUserPersonalOrders")
    public R getUserPersonalOrders(HttpServletRequest request){
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            System.out.println("userId=="+userId);
            QueryWrapper<PersonalOrders> personalOrdersQueryWrapper = new QueryWrapper<>();
            personalOrdersQueryWrapper.eq("user_id",userId);
            List<PersonalOrders> personalOrders = personalOrdersMapper.selectList(personalOrdersQueryWrapper);
            return R.ok().data("personalOrders",personalOrders).message("获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error().message("获取失败！");
    }

}
