package org.zhl.dht;

import io.vertx.core.json.JsonObject;
import lombok.*;
import org.zhl.bencode.Bencode;
import org.zhl.bencode.Type;
import org.zhl.holder.BencodeHolder;

import java.util.Map;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PingMessageRespDto {
    private String t;
    private String y;
    private String v;
    private R r;

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class R {
        private String id;
    }

}
