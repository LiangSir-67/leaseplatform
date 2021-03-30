package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.ManufacturerCommodity;
import cn.leaseplatform.entity.ManufacturerOrder;
import cn.leaseplatform.mapper.ManufacturerCommodityMapper;
import cn.leaseplatform.service.ManufacturerCommodityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/manufacturer-commodity")
@Api(tags = "厂商商品管理")
public class ManufacturerCommodityController {

    @Autowired
    private ManufacturerCommodityService manufacturerCommodityService;
    @Autowired
    private ManufacturerCommodityMapper commodityMapper;



    @ApiOperation(value = "获取所有商品")
    @GetMapping("/getcommoditys")
    public R getCommoditys(@RequestParam(defaultValue = "1") Integer currentPage){

        Page<ManufacturerCommodity> page = new Page<>(currentPage, 10);
        IPage<ManufacturerCommodity> manufacturerCommodityIPage =
                commodityMapper.selectPage(page,new QueryWrapper<ManufacturerCommodity>().orderByDesc("create_time"));
        return R.ok().data("commoditysinfo",manufacturerCommodityIPage);

    }
    @ApiOperation(value = "删除商品")
    @DeleteMapping("/deletecommoditys/{id}")
    public R deleteCommoditys(@ApiParam(name = "id",value = "商品id",required = true) @PathVariable Integer id){

        boolean flag =manufacturerCommodityService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }

    }

    @ApiOperation(value = "新增商品")
    @PostMapping("/insertcommoditys")
    public R saveCommoditys(@ApiParam(name = "manufacturerCommodity",value = "商品对象",required = true)
                                @RequestBody ManufacturerCommodity manufacturerCommodity){
        Boolean save =manufacturerCommodityService.save(manufacturerCommodity);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }

    }

    @ApiOperation(value = "商品详情")
    @GetMapping("/getcommoditysbyid/{id}")
    public R getCommoditysByid(@PathVariable Integer id){
        ManufacturerCommodity manufacturerCommodity=manufacturerCommodityService.getById(id);
        if(manufacturerCommodity!=null){
            return R.ok().data("manufacturerCommodity",manufacturerCommodity);
        }else{
            return R.error();
        }

    }

    @ApiOperation(value = "根据id修改商品")
    @PutMapping("/editcommoditys/{id}")
    public R editCommoditys(@PathVariable Integer id,@RequestBody ManufacturerCommodity manufacturerCommodity){


        manufacturerCommodity.setCommodityId(id);
        manufacturerCommodityService.updateById(manufacturerCommodity);
        return R.ok();

    }



}
