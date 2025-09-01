package dev.xavier72bit.bttn.common;

import lombok.Getter;

public enum ConfigKey {
    TRANSACTION_GENERATION_RATE("transaction.generation.rate.ms", Long.class),
    NODE_COUNT("node.count", Integer.class);

    @Getter
    private final String keyName;
    @Getter
    private final Class<?> type;

    ConfigKey(String keyName, Class<?> type) {
        this.keyName = keyName;
        this.type = type;
    }
}
