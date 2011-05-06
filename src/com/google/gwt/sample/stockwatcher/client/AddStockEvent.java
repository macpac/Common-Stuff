package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.GwtEvent;

public class AddStockEvent extends GwtEvent<AddStockEventHandler> {
	public static Type<AddStockEventHandler> TYPE = new Type<AddStockEventHandler>();

	@Override
	public Type<AddStockEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddStockEventHandler handler) {
		handler.onAddStock(this);
	}
}
