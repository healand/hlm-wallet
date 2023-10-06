package com.last.lastcoin.service;

import com.last.lastcoin.config.ScheduleConfig;
import com.last.lastcoin.dto.CoinInfo;
import com.last.lastcoin.enums.KeyValue;
import com.last.lastcoin.service.dao.KeyValueDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    @Autowired
    ScheduleConfig scheduleConfig;
    @Autowired
    OpenApiService openApiService;
    @Autowired
    LbankApiService lbankApiService;
    @Autowired
    KeyValueDaoService keyValueDaoService;

    @Scheduled(cron = "0 0/30 * * * *")
//    @Scheduled(cron = "0/10 * * * * *")
    public Integer saveDollarRate() {

        if(!scheduleConfig.isOpenApi())
            return -1;

        OpenApiService.MoneyToKrw moneyToKrw = openApiService.getKrwExchangeRate();
        Integer result = 0;

        result += keyValueDaoService.updateKeyValue(KeyValue.RATE_USD_WON.getKey(), String.valueOf(moneyToKrw.dollarToKrw));
        return result;
    }

//    @Scheduled(cron = "0 0/30 * * * *")

    @Scheduled(cron = "0/30 * * * * *") // 30초마다 실행
    public Integer saveLbankCoinInfo() {

        if(!scheduleConfig.isLbank())
            return -1;

        CoinInfo.CoinInfoResponse coinInfo = lbankApiService.getCoinInfo();
        Integer result = 0;

        result += keyValueDaoService.updateKeyValue(KeyValue.RATE_COIN_WON.getKey(), String.valueOf(coinInfo.getCoinToWon()));
        result += keyValueDaoService.updateKeyValue(KeyValue.CHANGE_RATE.getKey(), String.valueOf(coinInfo.getChangeRate()));
        return result;
    }
}
