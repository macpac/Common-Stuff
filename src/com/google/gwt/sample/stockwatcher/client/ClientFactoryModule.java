package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;


public class ClientFactoryModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(ClientFactory.class).to(ClientFactoryImpl.class).in(Singleton.class);
	}
}
