package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.GwtEvent;

public class RefreshStockListEvent extends GwtEvent<RefreshStockListEventHandler> {
	public static Type<RefreshStockListEventHandler> TYPE = new Type<RefreshStockListEventHandler>();

	@Override
	public Type<RefreshStockListEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RefreshStockListEventHandler handler) {
		handler.onRefreshStockList(this);
	}
}
