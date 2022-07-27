package io.github.educontessi.core.address.adapters.in.web.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"dateTimeCreation", "datTimeChange"})
public class CountryV1Dto extends BaseDto {

    private Long id;
    private String name;
    private String acronym;
    private String bacen;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getBacen() {
        return bacen;
    }

    public void setBacen(String bacen) {
        this.bacen = bacen;
    }
}
