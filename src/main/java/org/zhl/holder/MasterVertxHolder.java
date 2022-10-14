package org.zhl.holder;

import io.vertx.core.Vertx;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public  class MasterVertxHolder {

    private static class Temp {
        private static Vertx vertx = Vertx.vertx();

        public static Vertx getVertx() {
            return vertx;
        }
    }

    public static Vertx getVertx() {
        return Temp.getVertx();
    }

}
