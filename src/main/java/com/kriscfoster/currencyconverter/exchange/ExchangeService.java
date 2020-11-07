package com.kriscfoster.currencyconverter.exchange;

import com.kriscfoster.currencyconverter.currency.CurrencyEnum;
import com.kriscfoster.currencyconverter.rates.RatesProvider;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExchangeService {

    private RatesProvider ratesProvider;

    public ExchangeService(RatesProvider ratesProvider) {
        this.ratesProvider = ratesProvider;
    }

    public Number convert(CurrencyEnum fromCur, CurrencyEnum toCur, Date date, Number value) {
        Map<String, Number> rates = ratesProvider.getRates(date);
        Number baseValue = value.doubleValue() / rates.get(fromCur.toString()).doubleValue();
        Number convertedValue = baseValue.doubleValue() * rates.get(toCur.toString()).doubleValue();
        return Math.round(convertedValue.doubleValue()*100)/100.0d;
    }

    public List convertibleCurrencies() {
        List currencies = Arrays.asList(CurrencyEnum.values());
        return currencies;
    }
}
