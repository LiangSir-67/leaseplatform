package cn.leaseplatform.interceptors;

import cn.leaseplatform.utils.UserJwtTokenUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.google.gson.Gson;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-04-01-9:51
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> map = new HashMap<>();
        // 获取请求头中的令牌
        String token = request.getHeader("token");

        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断是否被ReleaseToken注解标记，是则放行
        ReleaseToken methodAnnotation = method.getAnnotation(ReleaseToken.class);
        if (methodAnnotation != null){
            return true;
        }

        // 判断请求的类是不是swagger的控制器，是则放行
        if (handlerMethod.getBean().getClass().getName().equals("springfox.documentation.swagger.web.ApiResourceController")){
            return true;
        }

        try {
            // 验证令牌
            UserJwtTokenUtils.verify(token);
            // 放行请求
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","无效签名");
        }
        // 设置状态
        map.put("state",false);
        // 响应给前台
        String tokenToJson = new Gson().toJson(map);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(tokenToJson);
        return false;
    }
}
