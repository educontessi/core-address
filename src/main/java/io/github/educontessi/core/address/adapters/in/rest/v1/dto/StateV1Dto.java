package io.github.educontessi.core.address.adapters.in.rest.v1.dto;

public class StateV1Dto extends BaseDto {

    private Long id;
    private String name;
    private String uf;
    private Long countryId;
    private CountryV1Dto country;

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

    public CountryV1Dto getCountry() {
        return country;
    }

    public void setCountry(CountryV1Dto country) {
        this.country = country;
    }
}
