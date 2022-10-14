package org.zhl.dto;

import io.vertx.core.datagram.DatagramSocket;
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
public class UdpSendDto<T>  implements ICode<T>{


    private String host;

    private int port;

    private T message;

}
