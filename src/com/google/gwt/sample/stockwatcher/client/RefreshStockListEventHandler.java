package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.EventHandler;

public interface RefreshStockListEventHandler extends EventHandler {
  void onRefreshStockList(RefreshStockListEvent event);
}
