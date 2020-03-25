package com.zlp.api.weixin.mp.handler;

import com.zlp.api.weixin.impl.entity.WechatKeyword;
import com.zlp.api.weixin.impl.mapper.KeywordMapper;
import com.zlp.api.weixin.mp.builder.TextBuilder;
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
        if (byKeyword!=null) {
            String keywordValue = byKeyword.getKeywordValue();
            return new TextBuilder().build(keywordValue,wxMessage,weixinService);
        }
       /* if (content.equals("蚂蚁课堂")) {
            return new TextBuilder().build("牛逼6666", wxMessage, weixinService);
        }
        if (content.equals("张立佩")) {
            return new TextBuilder().build("男，汉族，中国国籍，1988年8月8日出生，主要从事互联网Java架构师培训学员。", wxMessage, weixinService);
        }*/
        return new TextBuilder().build(defalutMsg, wxMessage, weixinService);

    }

}
