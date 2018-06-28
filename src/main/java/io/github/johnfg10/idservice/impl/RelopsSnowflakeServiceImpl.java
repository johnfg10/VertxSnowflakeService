package io.github.johnfg10.idservice.impl;

import com.relops.snowflake.Snowflake;
import io.github.johnfg10.idservice.IDService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public class RelopsSnowflakeServiceImpl implements IDService {
    private Snowflake snowflake;

    public RelopsSnowflakeServiceImpl(JsonObject config){
        snowflake = new Snowflake(config.getInteger("machineNode", 1));
    }

    @Override
    public void GenerateId(Handler<AsyncResult<Long>> handler) {
        handler.handle(Future.succeededFuture(snowflake.next()));
    }
}
