package com.zlp.api.member.impl.fegin;

import com.zlp.api.weixin.service.WeixinService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: MemberFegin
 * @Description: TODO(一句话描述该类的功能)
 * @Author: zhanglipei
 * @Date: 2020/3/7 17:31
 */
@FeignClient("mt-weixin")
public interface WeixinServiceFeign extends WeixinService {

//  没继承前，接口中有多少方法，就需要在fegin中写多少方法。继承后，就可以继承接口内的所有方法
// 微信接口测试
//    @GetMapping("appInfo")
//    String appInfgo(@RequestParam("userId") Integer userId);

}
