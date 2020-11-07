package com.kriscfoster.currencyconverter.rates;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kriscfoster.currencyconverter.currency.CurrencyEnum.*;

@Service
@ConditionalOnProperty(name="rates.provider.local", havingValue = "true")
public class LocalRatesProvider implements RatesProvider {
    @Override
    public Map<String, Number> getRates(Date date) {
        Map<String, Number> rates = new HashMap<>();
        rates.put(EUR.toString(), 0.846703);
        rates.put(GBP.toString(), 0.765623);
        rates.put(USD.toString(), 1);
        return rates;
    }
}
