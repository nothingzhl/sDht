package org.zhl.dht;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
@Data
@Builder
@ToString
public class PingMessageReqDto {

    private String t;
    private String y;
    private String q;
    private X a;

    @Data
    @Builder
    @ToString
    public static class X {
        private String id;
    }

}
