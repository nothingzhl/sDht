package org.zhl.holder;

import org.zhl.bencode.Bencode;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public class BencodeHolder {

    private static class Temp {
        private static Bencode bencode = new Bencode();

        public static Bencode getBencode() {
            return bencode;
        }
    }

    public static Bencode getBencode() {
        return Temp.getBencode();
    }
}
