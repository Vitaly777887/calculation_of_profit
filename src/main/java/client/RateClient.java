package client;

import entity.Currency;
import entity.RateResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

public class RateClient {
    private static final String ACCESS_KEY = "6e0719327899d8850ade09f10cb20353";
    private static final String SERVICE_URL = "http://data.fixer.io/api/";
    private static final String LATEST_RATES_URL = "latest";

    public static RateResponse getLatestResponse(Currency... currencies) {
        return getResponse(LATEST_RATES_URL, currencies);
    }

    public static RateResponse getHistoricalResponse(LocalDate date, Currency... currencies) {
        return getResponse(date.toString(), currencies);
    }

    private static RateResponse getResponse(String path, Currency... currencies) {
        Client client = ClientBuilder.newClient();
        return client
                .target(SERVICE_URL)
                .path(path)
                .queryParam("access_key", ACCESS_KEY)
                .queryParam("symbols", formatCurrencies(currencies))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(RateResponse.class);
    }

    private static String formatCurrencies(Currency... currencies) {
        StringBuilder sb = new StringBuilder();
        for (Currency currency : currencies) {
            sb.append(currency.name()).append(",");
        }
        return sb.toString();
    }
}
