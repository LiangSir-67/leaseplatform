package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.ManorderVo;
import cn.leaseplatform.entity.ManufacturerOrder;
import cn.leaseplatform.mapper.ManufacturerOrderMapper;
import cn.leaseplatform.service.ManufacturerOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getorderinfo")
    public R getOrderInfo(@RequestParam(defaultValue = "1") Integer currentPage){

        Page<ManufacturerOrder> page = new Page<>(currentPage, 10);
        IPage<ManufacturerOrder> manufacturerOrderIPage =
                manufacturerOrderMapper.selectPage(page,new QueryWrapper<ManufacturerOrder>().orderByDesc("create_time"));
        return R.ok().data("manufacturersInfo",manufacturerOrderIPage);
    }

    @ApiOperation(value = "连表查询订单信息")
    @GetMapping("/getorderinfo2")
    public R getOrderInfo2(){

        List<ManorderVo> manorderVo =manufacturerOrderService.getManorderVo();
        return R.ok().data("manorderVo",manorderVo);
    }

}
