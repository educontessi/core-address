package io.github.educontessi.core.address.adapters.in.v1.dto;

import io.github.educontessi.core.address.core.util.StringUtils;

public class StateDto extends BaseDto {

    private String name;
    private String uf;
    private Long countryId;
    private CountryDto country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.formatName(name);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }
}
