package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private final StockWatcherDisplay stockWatcherView = new StockWatcherView(
			this);

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	public StockWatcherDisplay getStockWatcherView() {
		return stockWatcherView;
	}
}