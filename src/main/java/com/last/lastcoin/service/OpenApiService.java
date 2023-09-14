package com.last.lastcoin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.last.lastcoin.config.OpenApiConfig;
import com.last.lastcoin.enums.KeyValue;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.service.dao.KeyValueDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final KeyValueDaoService keyValueDaoService;

    @Autowired
    private OkHttpClient client;

    @Autowired
    private OpenApiConfig openApiConfig;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String DOLLAR_EXCHANGE_RATE_URL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=%s&data=AP01";

    /**
     * 달러 환율
     *
     * @return
     */
    public MoneyToKrw getKrwExchangeRate() {
        log.info("getDollarExchangeRate()");
        List<Map> resultList;

        String url = String.format(DOLLAR_EXCHANGE_RATE_URL, openApiConfig.authKey);

        Request request = new Request.Builder().url(url).get().build();
        log.info("getDollarExchangeRate > request > " + url);

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                log.error("getDollarExchangeRate > " + response.code() + "/" + response.body().string());
                throw new CustomResponseStatusException(ExceptionCode.EXTERNAL_API_DOLLAR_RATE, "");
            }

            String responseBody = response.body().string();
            log.info("getDollarExchangeRate > response > " + responseBody);

            resultList = objectMapper.readValue(responseBody, List.class);

            if (resultList.size() == 1) {
                if ((int) resultList.get(0).get("result") == 4) {
                    log.error(ExceptionCode.EXTERNAL_API_DOLLAR_RATE_OVER_COUNT.getMessage());
                    throw new CustomResponseStatusException(ExceptionCode.EXTERNAL_API_DOLLAR_RATE_OVER_COUNT, "");
                }
            }

            double dollarToKrw = Double.parseDouble(String.valueOf(
                resultList.stream().filter(item -> item.get("cur_unit").equals("USD")).collect(Collectors.toList())
                    .get(0).get("bkpr")).replaceAll(",", ""));

            double dollarToIdr = Double.parseDouble(String.valueOf(
                resultList.stream().filter(item -> item.get("cur_unit").equals("IDR(100)")).collect(Collectors.toList())
                    .get(0).get("deal_bas_r")).replaceAll(",", ""));

            return new MoneyToKrw(dollarToKrw);
        } catch (IOException e) {

            throw new CustomResponseStatusException(ExceptionCode.INTERNAL_ERROR, "");
        }
    }

    class MoneyToKrw {
        double dollarToKrw;

        public MoneyToKrw(double dollarToKrw) {
            this.dollarToKrw = dollarToKrw;
        }
    }
}
