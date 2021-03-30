package cn.leaseplatform.servicebase.exceptionhandler;

import cn.leaseplatform.commonutils.ExceptionUtil;
import cn.leaseplatform.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error();
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(LPException.class)
    @ResponseBody
    public R error(LPException e){
        // 将异常信息写入文件中
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
