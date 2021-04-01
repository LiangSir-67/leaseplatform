package cn.leaseplatform.commonutils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenUtils {


    public static String getId(HttpServletRequest request){
        String Id = JwtUtils.getMemberIdByJwtToken(request);
        return Id;

    }

}
