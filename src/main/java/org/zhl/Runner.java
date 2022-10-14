package org.zhl;

import org.zhl.service.UdpDhtService;


/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public class Runner {


    public static void main(String[] args) {
        final UdpDhtService udpDhtService = new UdpDhtService();
        udpDhtService.start();
    }
}
