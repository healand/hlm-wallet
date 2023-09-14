package com.last.lastcoin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.last.lastcoin.dto.CoinInfo;
import com.last.lastcoin.dto.LbankTicker;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LbankApiService {

    private final KeyValueDaoService keyValueDaoService;

    @Autowired
    private OkHttpClient client;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public CoinInfo.CoinInfoResponse getCoinInfo() {
        String rateCoinUsdtUrl = keyValueDaoService.getKeyValue(KeyValue.RATE_COIN_USDT_URL).getValue();
        Double rateUsdWon = Double.parseDouble(keyValueDaoService.getKeyValue(KeyValue.RATE_USD_WON).getValue());
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request coinUsdtRequest = new Request.Builder().url(rateCoinUsdtUrl).get().build();
        Response coinResponse = null;

        try {
            coinResponse = client.newCall(coinUsdtRequest).execute();

            LbankTicker.ModelResponse response = objectMapper.readValue(coinResponse.body().string(), LbankTicker.ModelResponse.class);

            return CoinInfo.CoinInfoResponse.builder()
                    .changeRate(response.getTicker().getChange())
                    .coinToWon(response.getTicker().getLatest()*rateUsdWon)
                    .symbol(response.getSymbol())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public BigDecimal getHitopUsdtIndodax(int rateUsdtKrw, Double rate100IdrKrw) {
        String indodaxHitopIdrUrl = keyValueDaoService.getKeyValue(KeyValue.RATE_HITOP_IDR_INDODAX).getValue();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request indodaxHitopIdrRequest = new Request.Builder().url(indodaxHitopIdrUrl).get().build();
        Response hitopResponse = null;

        try {
            hitopResponse = client.newCall(indodaxHitopIdrRequest).execute();

            String responseString = hitopResponse.body().string();
            JsonNode response = objectMapper.readTree(responseString);
            Double rateHitopIdr = Double.parseDouble(response.get("ticker").get("last").asText());
            BigDecimal rateHitopKrw = new BigDecimal(rate100IdrKrw * rateHitopIdr / 100 / rateUsdtKrw);
            return rateHitopKrw;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
