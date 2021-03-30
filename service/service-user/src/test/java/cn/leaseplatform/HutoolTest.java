package cn.leaseplatform;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-30-16:08
 */
@SpringBootTest
public class HutoolTest {

    @Test
    public void test(){
        String password = "234324dsjflkdsjlk^&*^";
        String result = SecureUtil.md5(password);
        System.out.println(result);
    }
}
