package cn.leaseplatform.controller;

import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.servicebase.exceptionhandler.LPException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "测试")
@RestController
public class TestController {

    @ApiOperation(value = "这是统一返回结果测试！")
    @GetMapping("/test/hello")
    public R hello(){
        Map<String,String> data = new HashMap<>();
        data.put("name","亚索");
        data.put("age","22");
        return R.ok().data("userinfo",data);
    }

    /**
     * 用来测试统一异常结果返回的
     * @return
     */
    @ApiOperation(value = "这是异常测试！")
    @GetMapping("/test/exception")
    public R test(){
        try {
            // int i = 1/0;
            return R.error();
        }catch (Exception e){
            // 执行自定义异常
            throw new LPException(100,"执行了自定义异常LPException");
        }
    }
}
