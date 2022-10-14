package org.zhl;

import io.vertx.core.datagram.DatagramSocket;
import org.zhl.dto.UdpSendDto;
import org.zhl.service.UdpDhtService;

import java.util.Arrays;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public class Runner {

    private static String[] initServer = {"router.bittorrent.com", "router.utorrent.com", "dht.transmissionbt.com"};

    private static int initPort = 6881;

    public static void main(String[] args) {
        final UdpDhtService udpDhtService = new UdpDhtService();
        udpDhtService.start();
    }
}
