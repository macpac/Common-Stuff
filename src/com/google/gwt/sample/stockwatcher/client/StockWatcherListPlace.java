package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StockWatcherListPlace extends Place {
	private String token;

	public StockWatcherListPlace(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements
			PlaceTokenizer<StockWatcherListPlace> {
		@Override
		public String getToken(StockWatcherListPlace place) {
			return place.getToken();
		}

		@Override
		public StockWatcherListPlace getPlace(String token) {
			return new StockWatcherListPlace(token);
		}
	}
}
