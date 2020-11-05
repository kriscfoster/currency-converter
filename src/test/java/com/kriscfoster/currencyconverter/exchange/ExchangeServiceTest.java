package com.kriscfoster.currencyconverter.exchange;

import com.kriscfoster.currencyconverter.rates.RatesResponse;
import com.kriscfoster.currencyconverter.rates.RatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static com.kriscfoster.currencyconverter.currency.CurrencyEnum.EUR;
import static com.kriscfoster.currencyconverter.currency.CurrencyEnum.GBP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExchangeServiceTest {

    RatesService ratesService;
    RatesResponse ratesResponse;
    HashMap rates;

    @BeforeEach
    public void setup() {
        this.ratesService = mock(RatesService.class);
        this.ratesResponse = new RatesResponse();
        this.rates = new HashMap();
        rates.put(EUR.toString(), 0.846703);
        rates.put(GBP.toString(), 0.765623);
        ratesResponse.setRates(new HashMap<>());
    }

    @Test
    public void shouldExchangeEurToGbp() {
        Date today = new Date();
        when(ratesService.getRates(today)).thenReturn(rates);
        ExchangeService exchangeService = new ExchangeService(ratesService);
        assertEquals(90.42, exchangeService.convert(EUR, GBP, today, 100));
    }
}
