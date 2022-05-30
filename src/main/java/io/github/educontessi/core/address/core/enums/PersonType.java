package io.github.educontessi.core.address.core.enums;

public enum PersonType {

    PHYSICAL_PERSON("F", "Physical person"), LEGAL_PERSON("J", "Legal person");

    private final String type;
    private final String description;

    PersonType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
