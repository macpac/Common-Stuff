package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;

public interface StockWatcherDisplay extends IsWidget {
	HasClickHandlers getAddButton();

	void setRowData(int row, String priceText, String string);

	void selectAllInput();

	void addRow(String symbol, RemoveStockEvent removeStockEvent);

	void removeRow(int ro);

	HasText getLastUpdated();

	HasValue<String> getInput();

	HasKeyPressHandlers getInputAsKeyPressHandler();

	void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}
