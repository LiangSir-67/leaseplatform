package cn.leaseplatform.utils;


import cn.leaseplatform.commonutils.R;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-04-01-9:20
 */
public class UserJwtTokenUtils {

    private static final String SIGN = "!@#432wqe$%##fds234";

    /**
     * 生成token      header.payload.signature
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);  //默认七天过期
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())  //指定过期时间
                .sign(Algorithm.HMAC256(SIGN));//signature
        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }

    /**
     * 获取token信息
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }

    /**
     * 根据token获取当前角色信息
     * @param request
     * @return
     */
    public static String getInfoForToken(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = UserJwtTokenUtils.verify(token);
        String userId = verify.getClaim("userId").asString();
        return userId;
    }
}
