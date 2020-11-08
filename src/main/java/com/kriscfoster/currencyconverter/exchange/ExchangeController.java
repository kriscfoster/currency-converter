package com.kriscfoster.currencyconverter.exchange;

import com.kriscfoster.currencyconverter.currency.CurrencyEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public String getExchangePage(Model model) {
        List currencies = exchangeService.convertibleCurrencies();
        model.addAttribute("currencies", currencies);
        return "exchange";
    }

    @GetMapping("/exchange")
    public String getExchangePageWithConversion(
            @RequestParam CurrencyEnum fromCur,
            @RequestParam CurrencyEnum toCur,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
            @RequestParam Number value,
            Model model) {
        Number converted = exchangeService.convert(fromCur, toCur, date, value);
        List currencies = exchangeService.convertibleCurrencies();
        model.addAttribute("currencies", currencies);
        model.addAttribute("conversion", String.format("%s %s = %s %s",
                value, fromCur, converted, toCur));
        return "exchange";
    }
}
