package com.last.lastcoin.enums;

public enum WalletType {
    NORMAL(0),
    LOCKUP(1);

    private int value;

    WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
