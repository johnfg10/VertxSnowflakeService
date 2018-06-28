package io.github.johnfg10.idservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;


public class IDServiceVerticle extends AbstractVerticle {
    public final static String ADDRESS = "io.github.johnfg10.idgen";
    private IDService service;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        service = IDService.create(config());
        String address = config().getString("address", ADDRESS);
        new ServiceBinder(vertx).setAddress(address).register(IDService.class, service);
        startFuture.complete();
    }
}
