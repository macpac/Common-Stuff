package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientFactoryModule.class)
public interface ClientFactoryInjector extends Ginjector {
	ClientFactory getClientFactory();
}
