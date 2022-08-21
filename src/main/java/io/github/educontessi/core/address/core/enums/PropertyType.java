package io.github.educontessi.core.address.core.enums;

/**
 * @author Eduardo Possamai Contessi
 */
public enum PropertyType {

    APARTMENT("Apartment"),
    HOUSE("House"),
    COMMERCIAL("Commercial"),
    OTHER("Other");

    private final String description;

    PropertyType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
