package io.github.johnfg10.idservice;

import io.github.johnfg10.idservice.impl.RelopsSnowflakeServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface IDService {
    static IDService create(JsonObject config){
        return new RelopsSnowflakeServiceImpl(config);
    }

    static IDService createProxy(Vertx vertx, String address) {
        return new IDServiceVertxEBProxy(vertx, address);
    }

    public void GenerateId(Handler<AsyncResult<Long>> handler);
}
