package org.zhl.service;

import io.vertx.core.json.JsonObject;
import lombok.Getter;
import org.zhl.enums.DhtMessageTypeEnum;

/**
 * @author zhanghanlin
 * @date 2022/10/14
 **/
@Getter
public abstract class AbstractDhtMessageService {

    public DhtMessageTypeEnum typeEnum;

    public AbstractDhtMessageService(DhtMessageTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public abstract void handler(JsonObject source);
}
