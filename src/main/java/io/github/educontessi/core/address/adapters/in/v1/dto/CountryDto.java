package io.github.educontessi.core.address.adapters.in.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.educontessi.core.address.core.util.StringUtils;

@JsonIgnoreProperties(value = {"dateTimeCreation", "datTimeChange"})
public class CountryDto extends BaseDto {

    private String name;
    private String acronym;
    private String bacen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.formatName(name);
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
