package org.zhl.holder;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public class ServersHolder {
    public static Set<ImmutablePair<String, Integer>> initServerSets = new CopyOnWriteArraySet<>();

    static {
        String[] initServers = {"router.bittorrent.com", "router.utorrent.com", "dht.transmissionbt.com"};
        Arrays.stream(initServers).map(item -> ImmutablePair.of(item, 6881)).forEach(item -> initServerSets.add(item));
    }

    public static boolean contains(String host, int port) {
        return initServerSets.contains(ImmutablePair.of(host, port));
    }

    public static boolean remove(String host, int port) {
        return initServerSets.remove(ImmutablePair.of(host, port));
    }

    public static boolean add(String host, int port) {
        return initServerSets.add(ImmutablePair.of(host, port));
    }

    public static Set<ImmutablePair<String, Integer>> getInitServerSets() {
        return initServerSets;
    }
}
