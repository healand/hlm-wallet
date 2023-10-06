package com.last.lastcoin.enums;

public enum HistoryType {
    HISTORY_WITHDRAW_INSIDE("10"),
    HISTORY_WITHDRAW_LOCK_UP_REAL("11"),
    HISTORY_WITHDRAW_OUTSIDE("12"),
    HISTORY_WITHDRAW_LOCK_UP_DATA("13"),
    HISTORY_WITHDRAW_ETC("19"),
    HISTORY_DEPOSIT_INSIDE("20"),
    HISTORY_DEPOSIT_LOCK_UP("21"),
    HISTORY_DEPOSIT_OUTSIDE("22"),
    HISTORY_DEPOSIT_TRANSACTION_FEE("28"),
    HISTORY_DEPOSIT_ETC("29"),

    HISTORY_DEPOSIT_ADMIN("31"),
    HISTORY_WITHDRAW_ADMIN("32");

    private String value;

    HistoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
