package io.github.johnfg10;

import io.github.johnfg10.idservice.IDServiceVerticle;
import io.github.johnfg10.idservice.IDServiceVertxEBProxy;
import io.github.johnfg10.idservice.IDServiceVertxProxyHandler;
import io.github.johnfg10.idservice.impl.RelopsSnowflakeServiceImpl;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

public class EventBusTests extends IDServiceTestBase {

    @Test
    public void testGenerateIdRaw(TestContext ctx) throws Exception {
        Async async = ctx.async();
        DeliveryOptions opts = new DeliveryOptions();
        opts.addHeader("action", "GenerateId");
        vertx.eventBus().send(IDServiceVerticle.ADDRESS, null, opts, reply -> {
            if (reply.failed()){
                ctx.fail(reply.cause());
            }
            ctx.assertNotNull(reply.result().body());
            ctx.assertTrue(reply.result().body() instanceof Long, "Reply result was not of type Long");
            System.out.println(reply.result().body());
            async.complete();
        });
    }

    @Test
    public void testGenerateIdProxy(TestContext ctx) throws Exception {
        Async async = ctx.async();
        IDServiceVertxEBProxy proxy = new IDServiceVertxEBProxy(vertx, IDServiceVerticle.ADDRESS);
        proxy.GenerateId(res -> {
            if (res.failed()){
                ctx.fail(res.cause());
            }
            ctx.assertNotNull(res.result());
            System.out.println(res.result());
            async.complete();
        });
    }


}
