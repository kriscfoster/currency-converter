package com.kriscfoster.currencyconverter.exchange;

import com.kriscfoster.currencyconverter.currency.CurrencyEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public Number getConversion(
            @RequestParam CurrencyEnum fromCur,
            @RequestParam CurrencyEnum toCur,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
            @RequestParam Number value) {
        return exchangeService.convert(fromCur, toCur, date, value);
    }
}
