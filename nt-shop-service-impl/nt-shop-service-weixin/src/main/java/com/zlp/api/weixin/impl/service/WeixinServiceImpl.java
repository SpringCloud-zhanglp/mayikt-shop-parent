package com.zlp.api.weixin.impl.service;


import com.zlp.api.weixin.impl.entity.WechatKeyword;
import com.zlp.api.weixin.impl.mapper.KeywordMapper;
import com.zlp.api.weixin.service.WeixinService;
import com.zlp.base.BaseApiService;
import com.zlp.base.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ProjectName: nt-shop-parent
 * @ClassName: WeixinServiceImpl
 * @Description: TODO(微信接口的实现)
 * @Author: zhanglipei
 * @Date: 2020/3/7 15:51
 */
@RestController
public class WeixinServiceImpl extends BaseApiService implements WeixinService {

    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public String appInfo(@RequestParam("userId") Integer userId) {
        return "微信接口" + userId;
    }

    @Override
    public String getName(String name) {
        return "fegin继承后的调用 ：" + name;
    }

    @Override
    public BaseResponse<String> addApp(@RequestParam("appId") String appId, @RequestParam("appPwd") String appPwd) {
        if (StringUtils.isEmpty(appId)) {
            return setResultError("用户名不能为空！");
        }
        if (StringUtils.isEmpty(appPwd)) {
            return setResultError("密码不能为空");
        }

        return setResultSuccess("蚂蚁课堂牛。。");
    }


    @RequestMapping("/getValue")
    public String getValue(@RequestParam("key") String key) {
        WechatKeyword byKeyword = keywordMapper.findByKeyword(key);
        if (byKeyword != null) {
            String keywordValue = byKeyword.getKeywordValue();
            return keywordValue;
        }
        return "错了。。。";
    }
}
