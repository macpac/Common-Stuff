package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.stockwatcher.client.StockWatcherDisplay.Presenter;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class StockWatcherActivity extends AbstractActivity implements Presenter {
	private StockWatcherDisplay display;
	private EventBus eventBus;
	private ArrayList<String> stocks = new ArrayList<String>();
	private final ClientFactory clientFactory;
	private Timer timer;

	public StockWatcherActivity(StockWatcherListPlace place,
			ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	public void bind() {
		this.display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addStock();
			}

		});

		this.display.getInputAsKeyPressHandler().addKeyPressHandler(
				new KeyPressHandler() {
					public void onKeyPress(KeyPressEvent event) {
						if (event.getCharCode() == KeyCodes.KEY_ENTER) {
							addStock();
						}
					}
				});

		eventBus.addHandler(RefreshStockListEvent.TYPE,
				new RefreshStockListEventHandler() {
					@Override
					public void onRefreshStockList(RefreshStockListEvent event) {
						final double MAX_PRICE = 100.0; // $100.00
						final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

						StockPrice[] prices = new StockPrice[stocks.size()];
						for (int i = 0; i < stocks.size(); i++) {
							double price = Random.nextDouble() * MAX_PRICE;
							double change = price * MAX_PRICE_CHANGE
									* (Random.nextDouble() * 2.0 - 1.0);

							prices[i] = new StockPrice((String) stocks.get(i),
									price, change);
						}

						updateTable(prices);
					}
				});

		eventBus.addHandler(RemoveStockEvent.TYPE,
				new RemoveStockEventHandler() {
					@Override
					public void onRemoveStock(RemoveStockEvent event) {
						int row = stocks.indexOf(event.getSymbol()) + 1;
						stocks.remove(event.getSymbol());
						display.removeRow(row);
					}
				});
	}

	private void updateTable(StockPrice[] prices) {
		for (StockPrice sp : prices) {
			updateTable(sp);
		}

		// change the last update timestamp
		display.getLastUpdated().setText(
				"Last updated: "
						+ DateTimeFormat.getFormat(
								PredefinedFormat.DATE_TIME_MEDIUM).format(
								new Date()));

	}

	private void updateTable(StockPrice stockPrice) {
		int row = stocks.indexOf(stockPrice.getSymbol()) + 1;
		String priceText = NumberFormat.getFormat("#,##0.00").format(
				stockPrice.getPrice());
		NumberFormat changeFormat = NumberFormat
				.getFormat("+#,##0.00;-#,##0.00");
		String changeText = changeFormat.format(stockPrice.getChange());
		String changePercentText = changeFormat.format(stockPrice
				.getChangePercent());

		display.setRowData(row, priceText, changeText + " ("
				+ changePercentText + "%)");
	}

	private void addStock() {

		final String symbol = display.getInput().getValue().toUpperCase()
				.trim();
		// newSymbolTextBox.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters,
		// or dots.
		if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
			Window.alert("'" + symbol + "' is not a valid symbol.");
			display.selectAllInput();
			return;
		}

		display.getInput().setValue("");

		if (stocks.contains(symbol)) {
			return;
		}
		stocks.add(symbol);
		display.addRow(symbol, new RemoveStockEvent(symbol));

		eventBus.fireEvent(new AddStockEvent());
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		StockWatcherDisplay stockWatcherView = clientFactory
				.getStockWatcherView();
		stockWatcherView.setPresenter(this);
		// stockWatcherView.setEventBus(eventBus);

		this.display = clientFactory.getStockWatcherView();
		this.eventBus = eventBus;

		bind();

		panel.setWidget(stockWatcherView.asWidget());
		
		timer = new Timer() {
			@Override
			public void run() {
				clientFactory.getEventBus().fireEvent(new RefreshStockListEvent());
			}
		};
		timer.scheduleRepeating(5000);

	}

	@Override
	public String mayStop() {
		return "About to stop";
	}
	
	@Override
	public void onStop() {
		timer.cancel();
		timer = null;
	}
}
