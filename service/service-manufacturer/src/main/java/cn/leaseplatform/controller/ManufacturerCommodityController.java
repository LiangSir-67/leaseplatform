package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.commonutils.TokenUtils;
import cn.leaseplatform.entity.ManufacturerCommodity;
import cn.leaseplatform.mapper.ManufacturerCommodityMapper;
import cn.leaseplatform.service.ManufacturerCommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    public R getCommoditys(HttpServletRequest request) {
        try {
            String ID = TokenUtils.getId(request);
            Long Manid = Long.parseLong(ID);
            List<ManufacturerCommodity> manufacturerCommodities = manufacturerCommodityService.getByManId(Manid);
            return R.ok().data("manfa", manufacturerCommodities);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.error();
        }

    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/deletecommoditys/{id}")
    public R deleteCommoditys(HttpServletRequest request, @ApiParam(name = "id", value = "商品id", required = true) @PathVariable Integer id) {
        String ID = TokenUtils.getId(request);
        //Long Manid = Long.parseLong(ID);
        System.out.println("删除商品的id为"+id);
        try {
            boolean flag = manufacturerCommodityService.removeById(id);
            if (flag) {
                return R.ok();
            } else {
                return R.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }

    @ApiOperation(value = "新增商品")
    @PostMapping("/insertcommoditys")
    public R saveCommoditys(HttpServletRequest request, @ApiParam(name = "manufacturerCommodity", value = "商品对象", required = true)
    @RequestBody ManufacturerCommodity manufacturerCommodity) {
        try {
            String ID = TokenUtils.getId(request);
            Long Manid = Long.parseLong(ID);
            System.out.println("商家ID="+Manid);
            manufacturerCommodity.setManufacturerId(Manid);
            Boolean save = manufacturerCommodityService.save(manufacturerCommodity);
            if (save) {
                return R.ok();
            } else {
                return R.error();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.error();
        }

    }

    @ApiOperation(value = "商品详情")
    @GetMapping("/getcommoditysbyid/{id}")
    public R getCommoditysByid(HttpServletRequest request,@PathVariable Integer id) {
        ManufacturerCommodity manufacturerCommodity = manufacturerCommodityService.getById(id);
        if (manufacturerCommodity != null) {
            return R.ok().data("manufacturerCommodity", manufacturerCommodity);
        } else {
            return R.error();
        }

    }

    @ApiOperation(value = "根据id修改商品")
    @PutMapping("/editcommoditys/{id}")
    public R editCommoditys(HttpServletRequest request,@PathVariable Integer id, @RequestBody ManufacturerCommodity manufacturerCommodity) {

        try {
            manufacturerCommodity.setCommodityId(id);
            manufacturerCommodityService.updateById(manufacturerCommodity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }


}
