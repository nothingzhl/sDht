package org.zhl.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.zhl.bencode.Bencode;
import org.zhl.bencode.Type;
import org.zhl.enums.DhtMessageTypeEnum;
import org.zhl.holder.BencodeHolder;
import org.zhl.holder.ServersHolder;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
@Slf4j
public class PacketHandlerService {

    private Map<DhtMessageTypeEnum, AbstractDhtMessageService> serviceMap;

    public PacketHandlerService() {
        final Reflections reflections = new Reflections("org.zhl.service");
        serviceMap = reflections.getSubTypesOf(AbstractDhtMessageService.class).stream().map(item -> {
            AbstractDhtMessageService abstractDhtMessageService;
            try {
                abstractDhtMessageService = item.getConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return abstractDhtMessageService;
        }).collect(Collectors.toMap(AbstractDhtMessageService::getTypeEnum, Function.identity(), (p, n) -> p));

    }

    public void handlerResponseSuccess(AsyncResult<DatagramSocket> result) {
        result.map(item -> item.handler(packet -> {
            final String host = packet.sender().host();
            final int port = packet.sender().port();
            log.info("接收到:{}:{}的消息-{}", host, port, packet.data());
            ServersHolder.remove(host, port);
            final Bencode bencode = BencodeHolder.getBencode();
            final Map<String, Object> decode = bencode.decode(packet.data().getBytes(), Type.DICTIONARY);
            final JsonObject rJson = new JsonObject(decode);
            log.info("收到的数据是:{}", rJson);
            final DhtMessageTypeEnum rData = DhtMessageTypeEnum.getEnumByCode(rJson.getString("y"));
            if (Objects.isNull(rData)) {
                log.warn("对应的枚举找不到");
                return;
            }
            final AbstractDhtMessageService abstractDhtMessageService = serviceMap.get(rData);
            if (Objects.isNull(abstractDhtMessageService)) {
                log.warn("对应的策略不存在");
                return;
            }
            abstractDhtMessageService.handler(rJson);
        })).succeeded();
    }

}
