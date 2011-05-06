package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcherView extends Composite implements StockWatcherDisplay {
	private static final AppConstants CONSTANTS = GWT
			.create(AppConstants.class);
	private VerticalPanel mainPanel;
	private FlexTable stocksFlexTable;
	private TextBox newSymbolTextBox;
	private Button addButton;
	private Label lastUpdatedLabel;
	private Image image;
	EventBus eventBus;
	private Presenter presenter;

	SimplePanel viewPanel = new SimplePanel();

	public StockWatcherView(ClientFactory clientFactory) {
		this.eventBus = clientFactory.getEventBus();
		initWidget(viewPanel);
		onInit();
	}

	public void onInit() {
		mainPanel = new VerticalPanel();
		mainPanel.setSize("301px", "300px");
		viewPanel.add(mainPanel);

		image = new Image("images/googlecode.png");
		mainPanel.add(image);

		stocksFlexTable = new FlexTable();
		stocksFlexTable.setStyleName("watchList");
		// Add these lines
		stocksFlexTable.setText(0, 0, CONSTANTS.Symbol());
		stocksFlexTable.setText(0, 1, CONSTANTS.Price());
		stocksFlexTable.setText(0, 2, CONSTANTS.Change());
		stocksFlexTable.setText(0, 3, CONSTANTS.Remove());

		stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");

		mainPanel.add(stocksFlexTable);

		HorizontalPanel addPanel = new HorizontalPanel();
		mainPanel.add(addPanel);

		newSymbolTextBox = new TextBox();
		newSymbolTextBox.setFocus(true);
		addPanel.add(newSymbolTextBox);
		addButton = new Button(CONSTANTS.addButton_html());
		addButton.setStyleName("gwt-Button-add");
		addPanel.add(addButton);

		lastUpdatedLabel = new Label();
		mainPanel.add(lastUpdatedLabel);
		lastUpdatedLabel.setStyleName("gwt-Label-stockwatcher");

	}

	@Override
	public void addRow(String symbol, final RemoveStockEvent removeStockEvent) {
		int row = stocksFlexTable.getRowCount();
		stocksFlexTable.setText(row, 0, symbol);
		Button removeStock = new Button();
		removeStock.setText("x");
		removeStock.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(removeStockEvent);
			}
		});
		stocksFlexTable.setWidget(row, 3, removeStock);
	}

	@Override
	public HasValue<String> getInput() {
		return newSymbolTextBox;
	}

	@Override
	public HasKeyPressHandlers getInputAsKeyPressHandler() {
		return newSymbolTextBox;
	}

	@Override
	public Widget asWidget() {
		return mainPanel;
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	@Override
	public HasText getLastUpdated() {
		return lastUpdatedLabel;
	}

	@Override
	public void selectAllInput() {
		newSymbolTextBox.selectAll();
	}

	@Override
	public void setRowData(int row, String priceText, String changePercentage) {
		stocksFlexTable.setText(row, 1, priceText);
		stocksFlexTable.setText(row, 2, changePercentage);
	}

	@Override
	public void removeRow(int row) {
		stocksFlexTable.removeRow(row);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
