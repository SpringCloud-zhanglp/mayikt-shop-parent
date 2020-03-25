package com.zlp.api.weixin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: AppWeixin
 * @Description: TODO(微信启动类)
 * @Author: zhanglipei
 * @Date: 2020/3/7 15:55
 */
@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("com.zlp.api.weixin.impl.mapper")
public class AppWeixin {

    public static void main(String[] args) {
        SpringApplication.run(AppWeixin.class);
    }
}
