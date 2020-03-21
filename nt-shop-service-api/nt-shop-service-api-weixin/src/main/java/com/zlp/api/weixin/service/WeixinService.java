package com.zlp.api.weixin.service;

import com.zlp.base.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: Weixin
 * @Description: TODO(微信对外提供的接口)
 * @Author: zhanglipei
 * @Date: 2020/3/7 15:41
 */
@Api(tags = "微信服务基本接口")
public interface WeixinService {

    //微信接口测试
    @GetMapping("appInfo")
    @ApiOperation("appInfo接口")
    @ApiImplicitParam(name = "useId", value = "用户ID", required = true)
    @ApiResponse(code = 200, message = "响应成功")
    String appInfo(@RequestParam("userId") Integer userId);

    @GetMapping("getName")
    String getName(@RequestParam("name") String name);


    @GetMapping("addApp")
    @ApiOperation("addApp接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "appId", value = "用户ID", required = true),
                    @ApiImplicitParam(name = "appPwd", value = "用户密码", required = true)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "响应成功"),
            @ApiResponse(code = 500, message = "系统错误")
    })
    BaseResponse<String> addApp(@RequestParam("appId") String appId, @RequestParam("appPwd") String appPwd);
}
