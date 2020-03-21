package com.zlp.api.member.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: MemberService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: zhanglipei
 * @Date: 2020/3/14 15:45
 */
@Api(tags = "会员基本服务接口")
public interface MemberService {

    /**
     * 会员服务接口
     *
     * @param UserId
     * @return
     */
    @GetMapping("memberInterface")
    @ApiOperation("会员自己的接口")
    @ApiImplicitParam(name = "userId", value = "userId", required = true)
    @ApiResponse(code = 200, message = "响应成功")
    String memberInterface(@RequestParam("userId") Integer UserId);
}
