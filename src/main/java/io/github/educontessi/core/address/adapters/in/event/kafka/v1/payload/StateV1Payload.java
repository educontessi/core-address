package io.github.educontessi.core.address.adapters.in.event.kafka.v1.payload;

public class StateV1Payload extends BasePayload {

    private Long id;
    private String name;
    private String uf;
    private Long countryId;
    private CountryV1Payload country;

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

    public CountryV1Payload getCountry() {
        return country;
    }

    public void setCountry(CountryV1Payload country) {
        this.country = country;
    }
}
