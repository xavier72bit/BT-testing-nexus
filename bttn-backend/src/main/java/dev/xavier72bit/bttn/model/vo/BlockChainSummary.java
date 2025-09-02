package dev.xavier72bit.bttn.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record BlockChainSummary(
        List<Block> blocks,
        @JsonProperty("total_difficulty")
        long totalDifficulty,
        @JsonProperty("total_length")
        long totalLength
) {
    public record Block(
            int difficulty,
            String hash,
            @JsonProperty("prev_hash")
            String prevHash
    ) {}
}
