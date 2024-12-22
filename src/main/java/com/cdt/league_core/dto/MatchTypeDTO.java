package com.cdt.league_core.dto;

import com.cdt.league_core.model.enums.MatchType;

public class MatchTypeDTO {
    private MatchType matchType;

    public MatchTypeDTO(MatchType matchType) {
        this.matchType = matchType;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }
}
