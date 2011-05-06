package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.GwtEvent;

public class RemoveStockEvent extends GwtEvent<RemoveStockEventHandler> {
	public static Type<RemoveStockEventHandler> TYPE = new Type<RemoveStockEventHandler>();

	private String symbol;

	public RemoveStockEvent(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public Type<RemoveStockEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RemoveStockEventHandler handler) {
		handler.onRemoveStock(this);
	}
}
