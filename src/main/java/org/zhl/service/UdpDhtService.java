package org.zhl.service;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import lombok.extern.slf4j.Slf4j;
import org.zhl.dto.UdpSendDto;
import org.zhl.holder.MasterVertxHolder;

import java.util.Objects;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
@Slf4j
public class UdpDhtService {

    private String listenHost;

    private int listenPort;

    private DatagramSocket udpSocket;

    public UdpDhtService() {
        this("0.0.0.0", 1234, null);
    }

    public UdpDhtService(String listenHost, int listenPort, DatagramSocket udpSocket) {
        this.listenHost = listenHost;
        this.listenPort = listenPort;
        this.udpSocket = Objects.isNull(udpSocket) ? createUdpServer() : udpSocket;
    }

    private DatagramSocket createUdpServer() {
        final DatagramSocketOptions datagramSocketOptions = new DatagramSocketOptions();
        return MasterVertxHolder.getVertx().createDatagramSocket(datagramSocketOptions);
    }

    private void listen() {
        udpSocket.listen(listenPort, listenHost, result -> {
            if (result.succeeded()) {

            } else {

            }
        });
    }

    public void send(UdpSendDto udpSendDto) {
        final int port = udpSendDto.getPort();
        final String host = udpSendDto.getHost();

        udpSocket.send(Buffer.buffer(udpSendDto.encode()), port, host, result -> {
            if (result.succeeded()) {

            } else {

            }
        });
    }

    public void start() {
        log.info("启动udpService");
        listen();
        log.info("启动成功");
    }

}
