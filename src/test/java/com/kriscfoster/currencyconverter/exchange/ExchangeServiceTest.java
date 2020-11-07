package com.kriscfoster.currencyconverter.exchange;

import com.kriscfoster.currencyconverter.rates.ExternalRatesProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kriscfoster.currencyconverter.currency.CurrencyEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExchangeServiceTest {

    ExchangeService exchangeService;

    @BeforeEach
    public void setup() {
        ExternalRatesProvider externalRatesProvider = mock(ExternalRatesProvider.class);
        Map<String, Number> rates = new HashMap();
        rates.put(EUR.toString(), 0.846703);
        rates.put(GBP.toString(), 0.765623);
        rates.put(USD.toString(), 1);
        when(externalRatesProvider.getRates(any())).thenReturn(rates);
        this.exchangeService = new ExchangeService(externalRatesProvider);
    }

    @Test
    public void shouldExchangeEurToGbp() {
        assertEquals(90.42, exchangeService.convert(EUR, GBP, new Date(), 100));
    }

    @Test
    public void shouldExchangeEurToUsd() {
        assertEquals(118.11, exchangeService.convert(EUR, USD, new Date(), 100));
    }
}
