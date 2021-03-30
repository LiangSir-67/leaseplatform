package cn.leaseplatform;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-29-14:08
 */
@SpringBootTest
public class ServiceSMATest {
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


    public static void main(String[] args) throws Exception {
        String accessKeyId = "LTAI4GFX9yG1tjhfcNYGtk7R";
        String accessKeySecret = "tq0oOwRCOgOmRkswuxOgooEowFw654";
        List<String> argsList = Arrays.asList(args);
        Client client = ServiceSMATest.createClient(accessKeyId,accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers("18111145277")
                .setSignName("我的谷粒在线视频学习网站")
                .setTemplateCode("SMS_193519666")
                .setTemplateParam("{\"code\":\"" + ServiceSMATest.createNewcode() + "\"}");
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        System.out.println(sendSmsResponse.body.code);
    }

    /**
     * 用于生成随机的验证码
     * @return
     */
    public static int createNewcode(){
        return (int)(Math.random()*9999)+100;  //每次调用生成一次四位数的随机数
    }

    @Test
    public void test(){
        System.out.println(ServiceSMATest.createNewcode());
    }

}
