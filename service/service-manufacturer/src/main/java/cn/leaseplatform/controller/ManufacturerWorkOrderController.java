package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.ManufacturerWorkOrder;
import cn.leaseplatform.mapper.ManufacturerWorkOrderMapper;
import cn.leaseplatform.service.ManufacturerWorkOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/manufacturer-work-order")
@Api(tags = "工单服务")
public class ManufacturerWorkOrderController {
    @Autowired
    private ManufacturerWorkOrderService manufacturerWorkOrderService;
    @Autowired
    private ManufacturerWorkOrderMapper manufacturerWorkOrderMapper;



    @ApiOperation(value = "获取工单信息")
    @GetMapping("/getworkorders")
    public R getWorkOrders(@RequestParam(defaultValue = "1") Integer currentPage){
        Page<ManufacturerWorkOrder> page =new Page<>(currentPage,10);
        IPage<ManufacturerWorkOrder> manufacturerWorkOrderIPage =
                manufacturerWorkOrderMapper.selectPage(page, new QueryWrapper<ManufacturerWorkOrder>().orderByDesc("create_time"));
        return R.ok().data("wordorderinfo",manufacturerWorkOrderIPage);
    }

    @ApiOperation(value = "根据id获取工单信息")
    @GetMapping("/getworkordersbyid/{id}")
    public R getWorkOrdersByid(@PathVariable Integer id){

        ManufacturerWorkOrder manufacturerWorkOrder = manufacturerWorkOrderService.getById(id);
        if(manufacturerWorkOrder!=null){
            return R.ok().data("manufacturerWorkOrderinfo",manufacturerWorkOrder);
        }else{
            return R.error();
        }
    }

    @ApiOperation(value = "修改工单信息")
    @PutMapping("/editworkorders/{id}")
    public R editWorkOrders(@PathVariable Integer id,@RequestBody ManufacturerWorkOrder manufacturerWorkOrder){
        manufacturerWorkOrder.setWordorderId(id);
        boolean flag=manufacturerWorkOrderService.updateById(manufacturerWorkOrder);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

}
