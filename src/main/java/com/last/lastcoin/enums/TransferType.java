package com.last.lastcoin.enums;

public enum TransferType {
    TRANSFER_ADMIN("1"),
    TRANSFER_OUTSIDE("2"),
    TRANSFER_INSIDE("3"),
    TRANSFER_LOCKUP_RELEASE("4"),
    TRANSFER_POINT("6");

    private String value;

    TransferType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
