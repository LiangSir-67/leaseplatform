package cn.leaseplatform.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    /**
     * 使用mp实现添加操作，这个方法执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("插入-自动填充时间...");
        this.strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }

    /**
     * 使用mp实现修改操作，这个方法执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("修改-自动填充时间...");
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }
}
