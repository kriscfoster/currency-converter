package com.kriscfoster.currencyconverter.rates;

import java.util.Date;
import java.util.Map;

public interface RatesProvider {
    Map<String, Number> getRates(Date date);
}
