package com.kriscfoster.currencyconverter.rates;

import java.io.Serializable;
import java.util.Map;

public class RatesResponse implements Serializable {
    private Map<String, Number> rates;

    public Map<String, Number> getRates() {
        return rates;
    }

    public void setRates(Map<String, Number> rates) {
        this.rates = rates;
    }
}
