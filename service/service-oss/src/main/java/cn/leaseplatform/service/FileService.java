package cn.leaseplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-30-11:47
 */
@Service
public interface FileService {

    /**
     * 文件上传到阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
