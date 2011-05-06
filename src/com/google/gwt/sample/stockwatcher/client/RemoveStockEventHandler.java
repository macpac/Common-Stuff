package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.EventHandler;

public interface RemoveStockEventHandler extends EventHandler {
  void onRemoveStock(RemoveStockEvent event);
}
