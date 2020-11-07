package com.kriscfoster.currencyconverter.rates;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@ConditionalOnProperty(name="rates.provider.external", havingValue = "true")
public class ExternalRatesProvider implements RatesProvider {

    private String accessKey;
    private final RestTemplate restTemplate;

    public ExternalRatesProvider(@Value("${open-exchange-rates.access-key}") String accessKey) {
        this.accessKey = accessKey;
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Number> getRates(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endpoint = "/api/historical/" + simpleDateFormat.format(date) + ".json?app_id=" + accessKey;
        RatesResponse result = restTemplate.getForObject("http://openexchangerates.org" + endpoint, RatesResponse.class);
        Map<String, Number> rates = result.getRates();
        return rates;
    }
}
