package com.zlp.api.member;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: AppMember
 * @Description: TODO(一句话描述该类的功能)
 * @Author: zhanglipei
 * @Date: 2020/3/7 17:33
 */
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2Doc
public class AppMember {
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class);
    }
}
