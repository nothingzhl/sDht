package org.zhl.dto;

import org.zhl.holder.BencodeHolder;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public interface ICode<T> {

    T getMessage();

    default byte[] encode() {
        return BencodeHolder.getBencode().encode(getMessage());
    }
}
