package dev.xavier72bit.bttn.model.vo;

public record MinerDebugResponse (
    Boolean success,
    String message,
    Object Data
) {}