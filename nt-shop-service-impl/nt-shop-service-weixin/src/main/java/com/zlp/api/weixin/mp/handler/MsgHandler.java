package com.zlp.api.weixin.mp.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlp.api.weixin.impl.entity.WechatKeyword;
import com.zlp.api.weixin.impl.mapper.KeywordMapper;
import com.zlp.api.weixin.mp.builder.TextBuilder;
import com.zlp.http.HttpClientUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Value("${wechat.defalutMsg}")
    private String defalutMsg;

    @Value("${wechat.rpcWeatherUrl}")
    private String rpcWeatherUrl;
    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
//        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);
        String content = wxMessage.getContent();
        WechatKeyword byKeyword = keywordMapper.findByKeyword(content);
        if (byKeyword != null) {
            String keywordValue = byKeyword.getKeywordValue();
            return new TextBuilder().build(keywordValue, wxMessage, weixinService);
        }
        if (content.equals("发财")) {
            return new TextBuilder().build("牛逼6666", wxMessage, weixinService);
        }
        if (content.equals("神兽")) {
            return new TextBuilder().build("男，汉族，中国国籍，1988年8月8日出生，主要从事互联网Java架构师培训学员。", wxMessage, weixinService);
        }

        String newReplaceUrl = rpcWeatherUrl.replace("####", content);
        JSONObject resultJsonObject = HttpClientUtils.httpGet(newReplaceUrl);
        /*if (resultJsonObject != null) {

            return new TextBuilder().build(resultJsonObject.toJSONString(), wxMessage, weixinService);
        }*/
        if (resultJsonObject != null) {
            JSONArray results = resultJsonObject.getJSONArray("results");
            JSONObject resultsZeroJSONObject = results.getJSONObject(0);
            JSONObject locationJSONObject = resultsZeroJSONObject.getJSONObject("location");
            // 地址
            String path = locationJSONObject.getString("path");
            JSONObject nowJSONObject = resultsZeroJSONObject.getJSONObject("now");

            String text = nowJSONObject.getString("text");
            String temperature = nowJSONObject.getString("temperature");
            String lastUpdate = resultsZeroJSONObject.getString("last_update");
            String resultMsg = "您当前查询的城市" + content + ",天气为" + text + "天、实时温度为:" + temperature + "℃，" +
                    "最后更新的时间为:" + lastUpdate;
            return new TextBuilder().build(resultMsg, wxMessage, weixinService);
        }

        return new TextBuilder().build(defalutMsg, wxMessage, weixinService);

    }

}
