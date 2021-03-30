package cn.leaseplatform.controller;

import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-30-10:06
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin    // 跨域
@RestController
@RequestMapping("/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(name = "file",value = "文件",readOnly = true)
            @RequestParam("file")MultipartFile file
            ){
        String uploadUrl = fileService.upload(file);
        // 返回R对象
        return R.ok().message("文件上传成功").data("url",uploadUrl);
    }
}
