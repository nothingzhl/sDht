package org.zhl.service;

import io.vertx.core.json.JsonObject;
import org.zhl.enums.DhtMessageTypeEnum;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
public class DhtResponseService extends AbstractDhtMessageService {

    public DhtResponseService() {
        super(DhtMessageTypeEnum.RESPONSE);
    }

    @Override
    public void handler(JsonObject source) {

    }
}
