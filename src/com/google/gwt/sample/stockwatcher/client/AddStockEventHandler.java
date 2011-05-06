package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.EventHandler;

public interface AddStockEventHandler extends EventHandler {
  void onAddStock(AddStockEvent event);
}
