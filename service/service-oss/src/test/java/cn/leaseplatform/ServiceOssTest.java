package cn.leaseplatform;

import cn.leaseplatform.utils.ConstantPropertiesUtil;
import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-29-10:57
 */
@SpringBootTest
public class ServiceOssTest {


    String endpoint = "oss-cn-chengdu.aliyuncs.com";
    String accessKeyId = "LTAI5t9V6vrUs3a9gnC11nFZ";
    String accessKeySecret = "IdFzsQDwRanHVigtLfDOAuwxXuocoW";
    String bucketName = "leaseplatform";


    /**
     * 判断存储空间是否存在
     */
    @Test
    public void testExist(){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        boolean exist = ossClient.doesBucketExist(bucketName);
        System.out.println(ConstantPropertiesUtil.END_POINT);
        System.out.println(bucketName+" 存储空间是否存在："+exist);

        // 关闭OSSClient
        ossClient.shutdown();
    }
}
