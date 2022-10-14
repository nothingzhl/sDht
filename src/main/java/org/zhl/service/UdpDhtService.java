package org.zhl.service;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.zhl.dht.MessageFactory;
import org.zhl.dht.PingMessageReqDto;
import org.zhl.dto.UdpSendDto;
import org.zhl.holder.ServersHolder;
import org.zhl.holder.MasterVertxHolder;

import java.util.Objects;
import java.util.Set;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
@Slf4j
public class UdpDhtService {

    private String listenHost;

    private int listenPort;

    private DatagramSocket udpSocket;

    private PacketHandlerService packetHandlerService;

    public UdpDhtService() {
        this("0.0.0.0", 1234, null);
    }

    public UdpDhtService(String listenHost, int listenPort, DatagramSocket udpSocket) {
        this.listenHost = listenHost;
        this.listenPort = listenPort;
        this.udpSocket = Objects.isNull(udpSocket) ? createUdpServer() : udpSocket;
        this.packetHandlerService = new PacketHandlerService();
    }

    private DatagramSocket createUdpServer() {
        final DatagramSocketOptions datagramSocketOptions = new DatagramSocketOptions();
        return MasterVertxHolder.getVertx().createDatagramSocket(datagramSocketOptions);
    }

    private void listen() {
        udpSocket.listen(listenPort, listenHost, result -> packetHandlerService.handlerResponseSuccess(result));
    }

    public void send(UdpSendDto udpSendDto) {
        final int port = udpSendDto.getPort();
        final String host = udpSendDto.getHost();
        udpSocket.send(Buffer.buffer(udpSendDto.encode()), port, host, result -> {
            if (result.succeeded()) {
                log.info("{}:{} 发送成功!", host, port);
            } else {
                log.warn("{}:{} 发送失败!", host, port);
            }
        });
    }

    private void initSend() {
        final Set<ImmutablePair<String, Integer>> initServerSets = ServersHolder.getInitServerSets();
        log.info("初始化的服务列表是:{}",initServerSets);
        initServerSets.stream().map(item -> {
            PingMessageReqDto message = MessageFactory.buildQueryMessage("abcdefghij0123456789");
            return UdpSendDto.<PingMessageReqDto>builder().port(item.getRight()).host(item.getLeft()).message(message)
                .build();
        }).forEach(this::send);
    }

    public void start() {
        log.info("启动udpService");
        listen();
        MasterVertxHolder.getVertx().setPeriodic(4000, id -> initSend());
        initSend();
        log.info("启动成功");
    }

}
