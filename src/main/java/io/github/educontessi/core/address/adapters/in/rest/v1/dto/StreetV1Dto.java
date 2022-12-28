package io.github.educontessi.core.address.adapters.in.rest.v1.dto;

public class StreetV1Dto extends BaseDto {

    private Long id;
    private String name;
    private Long cityId;
    private CityV1Dto city;

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public CityV1Dto getCity() {
        return city;
    }

    public void setCity(CityV1Dto city) {
        this.city = city;
    }
}
