package com.last.lastcoin.enums;

public enum KeyValue {
    // 앱버전
    AOS_REQUIRE("aosRequire"),
    AOS_VERSION("aosVersion"),
    AOS_URL("aosUrl"),
    IOS_REQUIRE("iosRequire"),
    IOS_VERSION("iosVersion"),
    IOS_URL("iosUrl"),

    // SNS 채널 링크
    SNS_TWITTER("snsTwitter"),
    SNS_FACEBOOK("snsFacebook"),
    SNS_MEDIUM("snsMedium"),
    SNS_INSTAGRAM("snsInstagram"),
    SNS_DISCORD("snsDiscord"),
    SNS_TIKTOK("snsTiktok"),
    SNS_YOUTUBE("snsYoutube"),

    // 이용약관
    CONTRACT_SERVICE("contractServiceUrl"),
    CONTRACT_PERSONAL("contractPersonalUrl"),

    // 마켓
    RATE_COIN_USDT_URL("rateCoinUsdtUrl"),
//    RATE_COIN_USDT("rateCoinUsdt"),
    RATE_COIN_WON("rateCoinWon"),

    RATE_USD_WON("rateUsdWon"),
    //등락률
    CHANGE_RATE("changeRate");

    private String key;

    KeyValue(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
