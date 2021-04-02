package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.commonutils.TokenUtils;
import cn.leaseplatform.entity.ManOrderVo;
import cn.leaseplatform.mapper.ManufacturerOrderMapper;
import cn.leaseplatform.service.ManufacturerOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/manufacturerOrder")
@Api(tags = "厂商订单管理")
public class ManufacturerOrderController {

    @Autowired
    private ManufacturerOrderService manufacturerOrderService;
    @Autowired
    private ManufacturerOrderMapper manufacturerOrderMapper;

    @ApiOperation(value = "获取厂商订单信息")
    @GetMapping("/getorderinfo2")
    public R getOrderInfo(HttpServletRequest request){
        try {
            String ID = TokenUtils.getId(request);
            Long Manid = Long.parseLong(ID);
            System.out.println("厂商id"+Manid);
            List<ManOrderVo> manorderVo =manufacturerOrderService.getManorderVo(Manid);
            return R.ok().data("manorderVo",manorderVo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.error();
        }
    }


    @ApiOperation("根据订单编号获取订单")
    @PostMapping("/getorderbyorderid/{orderId}")
    public R getOrdertInfoByOrderId(HttpServletRequest request,@PathVariable Long orderId){
        try {
            String ID = TokenUtils.getId(request);
            Long Manid = Long.parseLong(ID);
            ManOrderVo manOrderVo = manufacturerOrderService.getOrdertInfoByOrderId(Manid,orderId);
            return R.ok().data("manOrderVo",manOrderVo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.error();
        }

    }



}
