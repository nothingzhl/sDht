package org.zhl.dht;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public class MessageFactory {

    public static PingMessageReqDto buildQueryMessage(String id){
        return PingMessageReqDto.builder().t("ping").y("q").q("ping").a(new PingMessageReqDto.X(id)).build();
    }

}
