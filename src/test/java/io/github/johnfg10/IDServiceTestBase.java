package io.github.johnfg10;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class IDServiceTestBase {

    protected Vertx vertx;

    @Before
    public void before(TestContext ctx){
        vertx = Vertx.vertx();
        vertx.deployVerticle("io.github.johnfg10.idservice.IDServiceVerticle", ctx.asyncAssertSuccess());
    }

    @After
    public void after(TestContext ctx){
        if (vertx != null){
            vertx.close(ctx.asyncAssertSuccess());
        }
    }
}
