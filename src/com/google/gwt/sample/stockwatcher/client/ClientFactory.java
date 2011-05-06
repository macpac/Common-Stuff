package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {
    EventBus getEventBus();
    PlaceController getPlaceController();
    StockWatcherDisplay getStockWatcherView();
}