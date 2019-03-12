package ru.tn.tnJetmanRating.enums;

import lombok.Getter;

@Getter
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
