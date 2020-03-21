package com.zlp.api.member.impl.service;

import com.zlp.api.member.impl.fegin.WeixinServiceFeign;
import com.zlp.api.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: MemberService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: zhanglipei
 * @Date: 2020/3/7 17:29
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeixinServiceFeign weixinServiceFeign;

    /**
     * 会员服务调用微信服务 。。
     *
     * @param userId
     * @return
     */
    @GetMapping("/memberToWeixin")
    public String memberToWeixin(Integer userId) {
        return "会员服务调用微信服务： " + weixinServiceFeign.appInfo(userId);
    }

    @GetMapping("memberToWeixinName")
    public String memberToWeixinName(String name) {
        return "继承fegin接口后，里面方法更牛逼了 ：：：" + weixinServiceFeign.getName(name);
    }

    @Override
    public String memberInterface(@RequestParam("userId") Integer UserId) {
        return "会员自己的接口。";
    }
}
